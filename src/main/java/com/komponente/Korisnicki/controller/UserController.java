package com.komponente.Korisnicki.controller;


import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }


    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<UserDto> updatePassword(@RequestBody UserChangePasswordDto userChangePasswordDto)
    {
        return new ResponseEntity<>(userService.updatePassword(userChangePasswordDto),HttpStatus.OK);
    }

}
