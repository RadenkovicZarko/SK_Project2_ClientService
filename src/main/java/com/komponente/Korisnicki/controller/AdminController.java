package com.komponente.Korisnicki.controller;

import com.komponente.Korisnicki.dto.ClientDto;
import com.komponente.Korisnicki.dto.ClientForbidenDto;
import com.komponente.Korisnicki.dto.RankCreateDto;
import com.komponente.Korisnicki.dto.RankDto;
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
    public ResponseEntity<ClientDto>  updateForbiden(@RequestHeader("Authorization") String authorization,@RequestBody @Valid ClientForbidenDto clientForbidenDto) {
        return new ResponseEntity<>(adminService.updateForbiden(clientForbidenDto), HttpStatus.OK);
    }


}
