package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.*;

public interface UserService {
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    DiscountDto findDiscount(Long id);

    UserDto updatePassword(UserChangePasswordDto userDto);
}
