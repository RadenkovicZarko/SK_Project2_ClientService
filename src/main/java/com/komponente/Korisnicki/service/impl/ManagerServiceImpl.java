package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.listener.helper.MessageHelper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private TokenService tokenService;
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String destination;

    public ManagerServiceImpl(TokenService tokenService, ManagerRepository managerRepository, ManagerMapper managerMapper, JmsTemplate jmsTemplate, MessageHelper messageHelper, @Value("${destination.emailDestination}") String destination) {
        this.tokenService = tokenService;
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.destination = destination;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(managerMapper::managerToManagerDto);
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 32;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        manager.setIsActivate(generatedString);
        managerRepository.save(manager);
        UniversalEmailDto universalEmailDto=new UniversalEmailDto("Registration",manager.getEmail(),manager.getFirstName(),manager.getLastName(),"http://localhost:8080/api/manager/"+manager.getIsActivate()+"/activate",(long)0,"","",null,null,"","");
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(universalEmailDto));
        //jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(new FirstEmailDto((long)1,"zarkoradenkovic2@gmail.com","zarko","radenkovic")));
        return  managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto activateAccount(String activateString) {
        Manager manager=managerRepository.findByIsActivate(activateString).orElseThrow(() -> new NotFoundException(String
                .format("Manager not found")));

        manager.setIsActivate("activated");
        managerRepository.save(manager);

        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto update(ManagerChangeParametersDto managerChangeParametersDto) {
        Manager manager= managerMapper.managerChangeParametersDtoToManager(managerChangeParametersDto);
        Claims c=tokenService.parseToken(managerChangeParametersDto.getToken());
        managerRepository.setManagerParametersById(manager.getFirstName(),manager.getLastName(),manager.getEmail(),manager.getDateOfBirth(),manager.getContactNo(),manager.getDateOfEmployment(),manager.getUsername(),c.get("id").toString());
        Manager manager1 = managerRepository.findById(new Long(c.get("id").toString())).orElseThrow(() -> new NotFoundException(String
                .format("Client not found")));
        return managerMapper.managerToManagerDto(manager1);
    }

    @Override
    public FullManagerDto findByIdToUpdate(SearchUserDto id) {
       Manager manager=managerRepository.findById(id.getId()).orElseThrow(() -> new NotFoundException(String
                .format("Manager not found")));
        return managerMapper.managerToFullManagerDto(manager);
    }


}
