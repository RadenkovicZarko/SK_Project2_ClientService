package com.komponente.Korisnicki.controller;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.security.CheckSecurity;
import com.komponente.Korisnicki.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController{

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/rank")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<RankDto> addRank( @RequestHeader("Authorization") String authorization,@RequestBody @Valid RankCreateDto rankCreateDto) {
        return new ResponseEntity<>(adminService.add(rankCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/forbidden")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto>  updateForbiden(@RequestHeader("Authorization") String authorization,@RequestBody @Valid ClientForbidenDto clientForbidenDto) {

        return new ResponseEntity<>(adminService.updateForbiden(clientForbidenDto), HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<AdminDto> updateClient(@RequestBody() @Valid AdminChangeParametersDto adminChangeParametersDto) {
        return new ResponseEntity<>(adminService.update(adminChangeParametersDto), HttpStatus.OK);
    }

    @PostMapping("/findByIdToChange")
    public ResponseEntity<FullAdminDto> findByIdToChange(@RequestBody SearchUserDto searchUserDto)
    {
        return new ResponseEntity<>(adminService.findByIdToUpdate(searchUserDto),HttpStatus.OK);
    }

}
