package com.komponente.Korisnicki.service.impl;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.exception.NotFoundException;
import com.komponente.Korisnicki.listener.helper.MessageHelper;
import com.komponente.Korisnicki.mapper.UserMapper;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.ClientRank;
import com.komponente.Korisnicki.model.Manager;
import com.komponente.Korisnicki.model.User;
import com.komponente.Korisnicki.repository.ClientRepository;
import com.komponente.Korisnicki.repository.ClientRankRepository;
import com.komponente.Korisnicki.repository.ManagerRepository;
import com.komponente.Korisnicki.repository.UserRepository;
import com.komponente.Korisnicki.service.TokenService;
import com.komponente.Korisnicki.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ManagerRepository managerRepository;
    private ClientRankRepository clientRankRepository;
    private UserMapper userMapper;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String destination;

    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, ClientRepository clientRepository, ClientRankRepository clientRankRepository,
                           UserMapper userMapper, JmsTemplate jmsTemplate, MessageHelper messageHelper,
                           @Value("${destination.emailDestination}")String destination,ManagerRepository managerRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.clientRepository = clientRepository;
        this.clientRankRepository = clientRankRepository;
        this.userMapper = userMapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.destination = destination;
        this.managerRepository=managerRepository;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Client with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
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
        int discount;
        try {
         discount=userStatusList.stream()
                    .filter(userStatus -> userStatus.getMaksi() >= client.getNumberOfRentingDays()
                            && userStatus.getMini() <= client.getNumberOfRentingDays())
                    .findAny()
                    .get()
                    .getPopust();
        }
        catch (Exception e)
        {
            discount=0;
        }
        return new DiscountDto(discount);
    }

    @Override
    public UserReservationDto findById(Long id) {
        User user=userRepository.findById(id).orElseThrow(() -> new NotFoundException(String
                .format("User with id: %d not found.",id)));
        return userMapper.userToUserReservationDto(user);
    }

    @Override
    public UserDto updatePassword(UserChangePasswordDto userChangePasswordDto) {
        User user=userRepository.findById(userChangePasswordDto.getId()).orElseThrow(() -> new NotFoundException(String
                .format("User with id: %d not found.", userChangePasswordDto.getId())));

        user.setPassword(userChangePasswordDto.getNewPassword());
        userRepository.save(user);
        UniversalEmailDto universalEmailDto=new UniversalEmailDto("PasswordChange",user.getEmail(),user.getFirstName(),user.getLastName(),"",(long)0,"","",null,null,"",user.getPassword());
        jmsTemplate.convertAndSend(destination,messageHelper.createTextMessage(universalEmailDto));
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<ClientAndManagerDto> findAllClientsAndManager() {
        List<ClientAndManagerDto> clientAndManagerDtos=new ArrayList<>();
        for(Client c:clientRepository.findAll())
        {
            clientAndManagerDtos.add(userMapper.clientToClientAndManagerdto(c));
        }
        for(Manager m:managerRepository.findAll())
        {
            clientAndManagerDtos.add(userMapper.managerToClientAndManagerdto(m));
        }
        return clientAndManagerDtos;

    }


}
