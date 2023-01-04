package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.ClientAndManagerDto;
import com.komponente.Korisnicki.dto.UserDto;
import com.komponente.Korisnicki.dto.UserReservationDto;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.Manager;
import com.komponente.Korisnicki.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setContactNo(user.getContactNo());
        return userDto;
    }
    public UserReservationDto userToUserReservationDto(User user)
    {
        UserReservationDto userDto=new UserReservationDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setContactNo(user.getContactNo());
        return userDto;
    }

    public ClientAndManagerDto clientToClientAndManagerdto(Client client)
    {
        ClientAndManagerDto clientAndManagerDto=new ClientAndManagerDto();
        clientAndManagerDto.setId(client.getId());
        clientAndManagerDto.setEmail(client.getEmail());
        clientAndManagerDto.setForbidden(client.isForbidden());
        return clientAndManagerDto;
    }

    public ClientAndManagerDto managerToClientAndManagerdto(Manager manager)
    {
        ClientAndManagerDto clientAndManagerDto=new ClientAndManagerDto();
        clientAndManagerDto.setId(manager.getId());
        clientAndManagerDto.setEmail(manager.getEmail());
        clientAndManagerDto.setForbidden(manager.isForbidden());
        return clientAndManagerDto;
    }

}
