package com.komponente.Korisnicki.service.impl;
import com.komponente.Korisnicki.dto.DiscountDto;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.Rank;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.repository.RankRepository;
import com.komponente.Korisnicki.service.AdminService;
import com.komponente.Korisnicki.service.ClientService;
import com.komponente.Korisnicki.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private ClientRepository clientRepository;
    private TokenService tokenService;
    private RankRepository rankRepository;

    public AdminServiceImpl(ClientRepository clientRepository, TokenService tokenService, RankRepository rankRepository) {
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
        this.rankRepository = rankRepository;
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %d not found.", id)));
        List<Rank> userStatusList = rankRepository.findAll();
        //get discount
        Integer discount = userStatusList.stream()
                .filter(userStatus -> userStatus.getMaxNumberOfRentingDays() >= client.getNumberOfRentingDays()
                        && userStatus.getMinNumberOfRentingDays() <= client.getNumberOfRentingDays())
                .findAny()
                .get()
                .getDiscount();
        return new DiscountDto(discount);
    }
}
