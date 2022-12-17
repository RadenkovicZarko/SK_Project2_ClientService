package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.service.TokenService;
import io.jsonwebtoken.Claims;

public class TokenServiceImpl implements TokenService {
    @Override
    public String generate(Claims claims) {
        return null;
    }

    @Override
    public Claims parseToken(String jwt) {
        return null;
    }
}
