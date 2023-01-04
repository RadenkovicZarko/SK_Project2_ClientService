package com.komponente.Korisnicki.controller;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.security.CheckSecurity;
import com.komponente.Korisnicki.service.ManagerService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ManagerDto>> getAllManagers(@RequestHeader("Authorization") String authorization,
                                                        Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ManagerDto> saveManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @GetMapping("/{activateString}/activate")
    public ResponseEntity<ManagerDto> activateAccount(@PathVariable("activateString") String activateString)
    {
        return new ResponseEntity<>(managerService.activateAccount(activateString),HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<ManagerDto> updateManager(@RequestBody() @Valid ManagerChangeParametersDto managerChangeParametersDto) {
        return new ResponseEntity<>(managerService.update(managerChangeParametersDto), HttpStatus.OK);
    }

    @PostMapping("/findByIdToChange")
    public ResponseEntity<FullManagerDto> findByIdToChange(@RequestBody SearchUserDto searchUserDto)
    {
        System.out.println("USAO");
        return new ResponseEntity<>(managerService.findByIdToUpdate(searchUserDto),HttpStatus.OK);
    }

}
