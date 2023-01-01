package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.UserDto;
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
        userDto.setRole(user.getRole());
        return userDto;
    }

}
