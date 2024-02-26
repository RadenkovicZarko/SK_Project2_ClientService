package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.*;
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


    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        return managerDto;
    }

    public Manager managerChangeParametersDtoToManager(ManagerChangeParametersDto managerChangeParametersDto)
    {
        Manager manager=new Manager();
        manager.setEmail(managerChangeParametersDto.getEmail());
        manager.setFirstName(managerChangeParametersDto.getFirstName());
        manager.setLastName(managerChangeParametersDto.getLastName());
        manager.setUsername(managerChangeParametersDto.getUsername());
        manager.setDateOfBirth(managerChangeParametersDto.getDateOfBirth());
        manager.setContactNo(managerChangeParametersDto.getContactNo());
        manager.setDateOfEmployment(managerChangeParametersDto.getDateOfEmployment());
        return manager;
    }

    public FullManagerDto managerToFullManagerDto(Manager manager)
    {
        FullManagerDto fullManagerDto=new FullManagerDto();
        fullManagerDto.setEmail(manager.getEmail());
        fullManagerDto.setFirstName(manager.getFirstName());
        fullManagerDto.setLastName(manager.getLastName());
        fullManagerDto.setUsername(manager.getUsername());
        fullManagerDto.setDateOfBirth(manager.getDateOfBirth());
        fullManagerDto.setContactNo(manager.getContactNo());
        fullManagerDto.setDateOfEmployment(manager.getDateOfEmployment());
        fullManagerDto.setPassword(manager.getPassword());
        return fullManagerDto;
    }
}

