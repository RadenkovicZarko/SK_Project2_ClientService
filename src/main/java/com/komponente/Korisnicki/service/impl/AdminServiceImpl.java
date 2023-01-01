package com.komponente.Korisnicki.service.impl;
import com.komponente.Korisnicki.dto.ClientDto;
import com.komponente.Korisnicki.dto.ClientForbidenDto;
import com.komponente.Korisnicki.dto.RankCreateDto;
import com.komponente.Korisnicki.dto.RankDto;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.mapper.ClientMapper;
import com.komponente.Korisnicki.mapper.RankMapper;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.ClientRank;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.repository.ClientRankRepository;
import com.komponente.Korisnicki.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    ClientRepository clientRepository;
    ClientMapper clientMapper;
    ClientRankRepository clientRankRepository;
    RankMapper rankMapper;

    public AdminServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, ClientRankRepository clientRankRepository, RankMapper rankMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.clientRankRepository = clientRankRepository;
        this.rankMapper = rankMapper;
    }

    @Override
    public RankDto add(RankCreateDto rankCreateDto) {
        ClientRank clientRank =rankMapper.rankDtoToRank(rankCreateDto);
        clientRankRepository.save(clientRank);
        return rankMapper.rankToRankDto(clientRank);
    }

    @Override
    public ClientDto updateForbiden(ClientForbidenDto clientForbidenDto) {
        Client client=clientRepository.findById(clientForbidenDto.getId()).orElseThrow(() -> new NotFoundException(String
                .format("Client not found.")));
        System.out.println(String.valueOf(clientForbidenDto.getId())+"  "+String.valueOf(client.isForbidden()));
        clientRepository.setClientForbiddenById(String.valueOf(client.isForbidden()),String.valueOf(clientForbidenDto.getId()));
        return clientMapper.clientToClientDto(client);
    }
}
