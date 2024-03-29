package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.ClientChangeParametersDto;
import com.komponente.Korisnicki.dto.ClientCreateDto;
import com.komponente.Korisnicki.dto.ClientDto;
import com.komponente.Korisnicki.dto.FullClientDto;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    private RoleRepository roleRepository;

    public ClientMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto)
    {
        Client client=new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setDateOfBirth(clientCreateDto.getDateOfBirth());
        client.setContactNo(clientCreateDto.getContactNo());
        client.setRole(roleRepository.findRoleByName("ROLE_CLIENT").get());
        client.setPassportNo(clientCreateDto.getPassportNo());
        client.setNumberOfRentingDays(0);
        client.setForbidden(false);

        return client;
    }

    public Client clientChangeParametersDtoToClient(ClientChangeParametersDto clientChangeParametersDto)
    {
        Client client=new Client();
        client.setEmail(clientChangeParametersDto.getEmail());
        client.setFirstName(clientChangeParametersDto.getFirstName());
        client.setLastName(clientChangeParametersDto.getLastName());
        client.setUsername(clientChangeParametersDto.getUsername());

        client.setDateOfBirth(clientChangeParametersDto.getDateOfBirth());
        client.setContactNo(clientChangeParametersDto.getContactNo());
        client.setRole(roleRepository.findRoleByName("ROLE_CLIENT").get());
        client.setPassportNo(clientChangeParametersDto.getPassportNo());
        client.setNumberOfRentingDays(0);
        client.setForbidden(false);

        return client;
    }


    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setForbidden(client.isForbidden());
        clientDto.setNumberOfRentingDays(client.getNumberOfRentingDays());
        clientDto.setActivateLink(client.getIsActivate());
        return clientDto;
    }


    public FullClientDto clientToFullClientDto(Client client)
    {
        FullClientDto fullClientDto=new FullClientDto();
        fullClientDto.setEmail(client.getEmail());
        fullClientDto.setFirstName(client.getFirstName());
        fullClientDto.setLastName(client.getLastName());
        fullClientDto.setUsername(client.getUsername());
        fullClientDto.setPassword(client.getPassword());
        fullClientDto.setDateOfBirth(client.getDateOfBirth());
        fullClientDto.setContactNo(client.getContactNo());
        fullClientDto.setPassportNo(client.getPassportNo());
        return fullClientDto;
    }
}
