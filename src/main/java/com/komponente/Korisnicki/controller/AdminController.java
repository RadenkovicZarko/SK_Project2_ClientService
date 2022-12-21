package com.komponente.Korisnicki.controller;

import com.komponente.Korisnicki.dto.ClientCreateDto;
import com.komponente.Korisnicki.dto.ClientDto;
import com.komponente.Korisnicki.dto.ClientForbidenDto;
import com.komponente.Korisnicki.dto.RankDto;
import com.komponente.Korisnicki.security.CheckSecurity;
import com.komponente.Korisnicki.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController{

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ApiOperation(value = "Add rank")
    @PostMapping("/rank")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<RankDto> addRank(@RequestBody @Valid RankDto rankDto) {
        return new ResponseEntity<>(adminService.add(rankDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change forbidden")
    @PostMapping("/forbidden")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ClientDto>  updateForbiden(@RequestBody @Valid ClientForbidenDto clientForbidenDto) {
        return new ResponseEntity<>(adminService.updateForbiden(clientForbidenDto), HttpStatus.OK);
    }


}
