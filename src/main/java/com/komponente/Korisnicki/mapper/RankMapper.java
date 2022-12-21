package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.RankDto;
import com.komponente.Korisnicki.model.ClientRank;
import org.springframework.stereotype.Component;

@Component
public class RankMapper {

    public ClientRank rankDtoToRank(RankDto rankDto) {
        ClientRank clientRank =new ClientRank(rankDto.getId(),rankDto.getMinNumberOfRentingDays(),rankDto.getMaxNumberOfRentingDays(),rankDto.getDiscount());
        return clientRank;
    }

    public RankDto rankToRankDto(ClientRank clientRank)
    {
        RankDto rankDto=new RankDto();
        rankDto.setId(clientRank.getId());
        rankDto.setDiscount(clientRank.getPopust());
        rankDto.setMaxNumberOfRentingDays(clientRank.getMaksi());
        rankDto.setMinNumberOfRentingDays(clientRank.getMini());
        return rankDto;
    }
}
