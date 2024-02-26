package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.RankCreateDto;
import com.komponente.Korisnicki.dto.RankDto;
import com.komponente.Korisnicki.model.ClientRank;
import org.springframework.stereotype.Component;

@Component
public class RankMapper {

    public ClientRank rankDtoToRank(RankCreateDto rankCreateDto) {
        ClientRank clientRank =new ClientRank(rankCreateDto.getMinNumberOfRentingDays(), rankCreateDto.getMaxNumberOfRentingDays(), rankCreateDto.getDiscount());
        return clientRank;
    }

    public RankDto rankToRankDto(ClientRank clientRank)
    {
        RankDto rankDto =new RankDto();
        rankDto.setId(clientRank.getId());
        rankDto.setDiscount(clientRank.getPopust());
        rankDto.setMaxNumberOfRentingDays(clientRank.getMaksi());
        rankDto.setMinNumberOfRentingDays(clientRank.getMini());
        return rankDto;
    }
}
