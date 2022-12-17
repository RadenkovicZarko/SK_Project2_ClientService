package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.mapper.ClientMapper;
import com.komponente.Korisnicki.mapper.ManagerMapper;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.Manager;
import com.komponente.Korisnicki.model.User;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.repository.ManagerRepository;
import com.komponente.Korisnicki.service.ManagerService;
import com.komponente.Korisnicki.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private TokenService tokenService;
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;



    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(managerMapper::clientToClientDto);
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return  managerMapper.clientToClientDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Manager manager = managerRepository
                .findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Manager with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        claims.put("role", manager.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
}