package com.komponente.Korisnicki.controller;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.security.CheckSecurity;
import com.komponente.Korisnicki.service.ClientService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Get all users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping
    @CheckSecurity(roles = {"ROLE_CLIENT"})
    public ResponseEntity<Page<ClientDto>> getAllClients(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Register user")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_CLIENT"})
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto userCreateDto) {
        return new ResponseEntity<>(clientService.add(userCreateDto), HttpStatus.CREATED);
    }


    @GetMapping("/discount")
    public ResponseEntity<DiscountDto> getDiscount(@RequestHeader("id") String id)
    {
        return new ResponseEntity<>(clientService.findDiscont(id),HttpStatus.OK);
    }



    //PRIMA DVA PARAMETRA, NE ZNAM KAKO DA JOJ PROSLEDIM OBA U POSTMANU
    @ApiOperation(value = "Change")
    @PostMapping("/change")
    @CheckSecurity(roles = {"ROLE_CLIENT"})
    public ResponseEntity<ClientDto> updateClient(@RequestBody() @Valid ClientChangeParametersDto clientChangeParametersDto) {
        return new ResponseEntity<>(clientService.update(clientChangeParametersDto), HttpStatus.OK);
    }

}
