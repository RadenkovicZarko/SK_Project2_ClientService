package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.TokenRequestDto;
import com.komponente.Korisnicki.dto.TokenResponseDto;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.User;
import com.komponente.Korisnicki.repository.UserRepository;
import com.komponente.Korisnicki.service.ClientService;
import com.komponente.Korisnicki.service.TokenService;
import com.komponente.Korisnicki.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TokenService tokenService;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findUserByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Client with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        if(user.getRole().getId()==1)
        {
            Client client=(Client) user;
            if(client.isForbidden())
            {
                return null;
            }
        }
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
