package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.mapper.ClientMapper;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.User;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.service.ClientService;
import com.komponente.Korisnicki.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(TokenService tokenService, ClientRepository clientRepository, ClientMapper clientMapper) {
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::clientToClientDto);
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        System.out.println("USAo");
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
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
//        if(!client.getFirstName().isEmpty() && !client.getFirstName().equals(""))
//            clientRepository.setClientFirstNameById(client.getFirstName(),c.get("id").toString());
//        if(!client.getLastName().isEmpty() && !client.getLastName().equals(""))
//            clientRepository.setClientLastNameById(client.getLastName(),c.get("id").toString());
//        if(!client.getEmail().isEmpty() && !client.getEmail().equals(""))
//            clientRepository.setClientEmailById(client.getEmail(),c.get("id").toString());
//        if(!client.getPassword().isEmpty() && !client.getPassword().equals(""))
//            clientRepository.setClientPasswordById(client.getPassword(),c.get("id").toString());
//        if(!client.getDateOfBirth().isEmpty() && !client.getDateOfBirth().equals(""))
//            clientRepository.setClientDateOfBirthById(client.getDateOfBirth(),c.get("id").toString());
//        if(!client.getContactNo().isEmpty() && !client.getContactNo().equals(""))
//            clientRepository.setClientContactNoById(client.getContactNo(),c.get("id").toString());
//        if(client.getPassportNo()!=0)
//            clientRepository.setClientPassportNoById(client.getPassportNo().toString(),c.get("id").toString());
//        if(client.getNumberOfRentingDays()!=0)
//        {
//            clientRepository.setClientNumberOfRentingDaysById(String.valueOf(client.getPassportNo()+client1.getNumberOfRentingDays()),c.get("id").toString());
//        }
        Client client1 = clientRepository.findById(new Long(c.get("id").toString())).orElseThrow(() -> new NotFoundException(String
                .format("Client not found")));
        return clientMapper.clientToClientDto(client1);
    }

//    @Override
//    public DiscountDto findDiscont(String token) {
//        Claims c=tokenService.parseToken(token);
//        Client client1 = clientRepository.findById(Long.parseLong(c.getId())).orElseThrow(() -> new NotFoundException(String
//                .format("Client not found")));
//        if(client1.getRank()==1)
//            return new DiscountDto(10);
//        else if(client1.getRank()==2)
//            return new DiscountDto(20);
//        else if(client1.getRank()==3)
//            return new DiscountDto(30);
//        else
//            return new DiscountDto(0);
//    }


}

