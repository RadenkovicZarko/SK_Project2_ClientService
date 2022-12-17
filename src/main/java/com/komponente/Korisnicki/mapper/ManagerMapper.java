package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.ClientCreateDto;
import com.komponente.Korisnicki.dto.ClientDto;
import com.komponente.Korisnicki.dto.ManagerCreateDto;
import com.komponente.Korisnicki.dto.ManagerDto;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.Manager;
import com.komponente.Korisnicki.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {

    private RoleRepository roleRepository;

    public ManagerMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto)
    {
        Manager manager=new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
        manager.setContactNo(managerCreateDto.getContactNo());
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        manager.setNameOfCompany(managerCreateDto.getNameOfCompany());
        manager.setDateOfEmployment(managerCreateDto.getDateOfEmployment());
        manager.setForbidden(false);
        return manager;
    }


    public ManagerDto clientToClientDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        return managerDto;
    }
}
