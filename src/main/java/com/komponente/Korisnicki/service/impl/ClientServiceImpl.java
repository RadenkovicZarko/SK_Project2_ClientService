package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.listener.helper.MessageHelper;
import com.komponente.Korisnicki.mapper.ClientMapper;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.User;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.service.ClientService;
import com.komponente.Korisnicki.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String destination;


    public ClientServiceImpl(TokenService tokenService, ClientRepository clientRepository, ClientMapper clientMapper, JmsTemplate jmsTemplate,
                             MessageHelper messageHelper, @Value("${destination.registrationEmail}")String destination) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.destination = destination;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::clientToClientDto);
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(new FirstEmailDto((long)1,"zarkoradenkovic2@gmail.com","zarko","radenkovic")));
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ClientDto find(TokenRequestDto tokenRequestDto) {
        Client client = clientRepository
                .findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Client with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        return clientMapper.clientToClientDto(client);
    }


    //KADA SE ULOGUJE KORISNIK, SACUVA NJEGOV TOKEN U NEKOJ PROMENLJIVOJ NA FRONTU, KADA HOCE NESTO DA PROMENI ONDA PROSLEDJUJEM NJEGOV TOKEN I TO STO ZELI DA PROMENI, MORA MALO DA SE POPRAVI
    @Override
    public ClientDto update(ClientChangeParametersDto clientChangeParametersDto) {
        Client client= clientMapper.clientChangeParametersDtoToClient(clientChangeParametersDto);
        Claims c=tokenService.parseToken(clientChangeParametersDto.getToken());
        clientRepository.setClientParametersById(client.getFirstName(),client.getLastName(),client.getEmail(),client.getPassword(),client.getDateOfBirth(),client.getContactNo(),client.getPassportNo().toString(),c.get("id").toString());
        Client client1 = clientRepository.findById(new Long(c.get("id").toString())).orElseThrow(() -> new NotFoundException(String
                .format("Client not found")));
        return clientMapper.clientToClientDto(client1);
    }



}

