package com.komponente.Korisnicki.service.impl;
import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.mapper.AdminMapper;
import com.komponente.Korisnicki.mapper.ClientMapper;
import com.komponente.Korisnicki.mapper.RankMapper;
import com.komponente.Korisnicki.mapper.UserMapper;
import com.komponente.Korisnicki.model.*;
import com.komponente.Korisnicki.repository.AdminRepository;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.repository.ClientRankRepository;
import com.komponente.Korisnicki.repository.UserRepository;
import com.komponente.Korisnicki.service.AdminService;
import com.komponente.Korisnicki.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    ClientRepository clientRepository;
    ClientMapper clientMapper;
    ClientRankRepository clientRankRepository;
    RankMapper rankMapper;
    AdminRepository adminRepository;
    AdminMapper adminMapper;
    private TokenService tokenService;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public AdminServiceImpl(UserMapper userMapper,UserRepository userRepository,ClientRepository clientRepository, ClientMapper clientMapper, ClientRankRepository clientRankRepository, RankMapper rankMapper, AdminRepository adminRepository, AdminMapper adminMapper, TokenService tokenService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.clientRankRepository = clientRankRepository;
        this.rankMapper = rankMapper;
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.tokenService = tokenService;
        this.userRepository=userRepository;
        this.userMapper=userMapper;
    }

    @Override
    public RankDto add(RankCreateDto rankCreateDto) {
        ClientRank clientRank =rankMapper.rankDtoToRank(rankCreateDto);
        clientRankRepository.save(clientRank);
        return rankMapper.rankToRankDto(clientRank);
    }

    @Override
    public UserDto updateForbiden(ClientForbidenDto clientForbidenDto) {
        User user=userRepository.findById(clientForbidenDto.getId()).orElseThrow(() -> new NotFoundException(String
                .format("User not found")));

        if(user.getRole().getName().equalsIgnoreCase("ROLE_CLIENT"))
        {
            Client client=(Client) user;
            client.setForbidden(clientForbidenDto.isForbidden());
            userRepository.save(client);
        }
        if(user.getRole().getName().equalsIgnoreCase("ROLE_MANAGER"))
        {
            Manager manager=(Manager) user;
            manager.setForbidden(clientForbidenDto.isForbidden());
            userRepository.save(manager);

        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public FullAdminDto findByIdToUpdate(SearchUserDto id) {
        Admin admin=adminRepository.findById(id.getId()).orElseThrow(() -> new NotFoundException(String
                .format("Admin not found")));
        return adminMapper.adminToFullAdminDto(admin);
    }

    @Override
    public AdminDto update(AdminChangeParametersDto adminChangeParametersDto) {
        Admin admin= adminMapper.adminChangeParametersDtoToAdmin(adminChangeParametersDto);
        Claims c=tokenService.parseToken(adminChangeParametersDto.getToken());
       adminRepository.setAdminParametersById(admin.getFirstName(),admin.getLastName(),admin.getEmail(),admin.getDateOfBirth(),admin.getContactNo(),admin.getUsername(),c.get("id").toString());
        Admin admin1 = adminRepository.findById(new Long(c.get("id").toString())).orElseThrow(() -> new NotFoundException(String
                .format("Admin not found")));
        return adminMapper.adminToAdminDto(admin1);
    }
}
