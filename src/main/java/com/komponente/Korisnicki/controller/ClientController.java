package com.komponente.Korisnicki.controller;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.security.CheckSecurity;
import com.komponente.Korisnicki.service.ClientService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService)
    {
        this.clientService=clientService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<ClientDto>> getAllClients(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto userCreateDto) {
        System.out.println("DESILO SE");
        return new ResponseEntity<>(clientService.add(userCreateDto), HttpStatus.CREATED);
    }


    //PRIMA DVA PARAMETRA, NE ZNAM KAKO DA JOJ PROSLEDIM OBA U POSTMANU
    @PostMapping("/change")
    public ResponseEntity<ClientDto> updateClient(@RequestBody() @Valid ClientChangeParametersDto clientChangeParametersDto) {
        return new ResponseEntity<>(clientService.update(clientChangeParametersDto), HttpStatus.OK);
    }

    @PostMapping("/updateRentingDays")
    public ResponseEntity<ClientDto> updateClientNumberOfRentingDays(@RequestBody() ClientRentingDaysDto clientRentingDaysDto)
    {
        System.out.println(clientRentingDaysDto.getId()+" "+ clientRentingDaysDto.getNumOfDays());
        return new ResponseEntity<>(clientService.updateClientNumberOfRentingDays(clientRentingDaysDto),HttpStatus.OK);
    }

    @GetMapping("/{activateString}/activate")
    public ResponseEntity<ClientDto> activateAccount(@PathVariable("activateString") String activateString)
    {
        return new ResponseEntity<>(clientService.activateAccount(activateString),HttpStatus.OK);
    }

    @PostMapping("/findByIdToChange")
    public ResponseEntity<FullClientDto> findByIdToChange(@RequestBody SearchUserDto searchUserDto)
    {
        return new ResponseEntity<>(clientService.findByIdToUpdate(searchUserDto),HttpStatus.OK);
    }



}
