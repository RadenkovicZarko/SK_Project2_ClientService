package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.TokenRequestDto;
import com.komponente.Korisnicki.dto.TokenResponseDto;

public interface UserService {
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
