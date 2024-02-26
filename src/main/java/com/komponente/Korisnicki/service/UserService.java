package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.*;

import java.util.List;

public interface UserService {
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    DiscountDto findDiscount(Long id);
    UserReservationDto findById(Long id);
    UserDto updatePassword(UserChangePasswordDto userDto);

    List<ClientAndManagerDto> findAllClientsAndManager();

}
