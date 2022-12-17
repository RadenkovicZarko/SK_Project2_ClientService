package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);

    ClientDto add(ClientCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ClientDto find(TokenRequestDto tokenRequestDto);

    ClientDto update(ClientChangeParametersDto clientChangeParametersDto,String token);

    DiscountDto findDiscont(String token);
}
