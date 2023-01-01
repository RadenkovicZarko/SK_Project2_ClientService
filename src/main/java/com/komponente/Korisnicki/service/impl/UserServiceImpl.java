package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.DiscountDto;
import com.komponente.Korisnicki.dto.TokenRequestDto;
import com.komponente.Korisnicki.dto.TokenResponseDto;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.ClientRank;
import com.komponente.Korisnicki.model.User;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.repository.ClientRankRepository;
import com.komponente.Korisnicki.repository.UserRepository;
import com.komponente.Korisnicki.service.TokenService;
import com.komponente.Korisnicki.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientRankRepository clientRankRepository;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, ClientRepository clientRepository, ClientRankRepository clientRankRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.clientRankRepository = clientRankRepository;
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
        if(user.getRole().getId()==3)
        {
            Client client=(Client) user;
            if(client.isForbidden())
            {
                return null;
            }
        }
        return new TokenResponseDto(tokenService.generate(claims));
    }


    @Override
    public DiscountDto findDiscount(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Client with id: %d not found.", id)));
        List<ClientRank> userStatusList = clientRankRepository.findAll();
        //get discount
        Integer discount = userStatusList.stream()
                .filter(userStatus -> userStatus.getMaksi() >= client.getNumberOfRentingDays()
                        && userStatus.getMini() <= client.getNumberOfRentingDays())
                .findAny()
                .get()
                .getPopust();
        return new DiscountDto(discount);
    }
}
