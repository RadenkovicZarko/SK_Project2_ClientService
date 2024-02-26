package com.komponente.Korisnicki.mapper;

import com.komponente.Korisnicki.dto.*;
import com.komponente.Korisnicki.model.Admin;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    private RoleRepository roleRepository;

    public  AdminMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Admin adminChangeParametersDtoToAdmin(AdminChangeParametersDto adminChangeParametersDto)
    {
        Admin admin=new Admin();
        admin.setEmail(adminChangeParametersDto.getEmail());
        admin.setFirstName(adminChangeParametersDto.getFirstName());
        admin.setLastName(adminChangeParametersDto.getLastName());
        admin.setUsername(adminChangeParametersDto.getUsername());

        admin.setDateOfBirth(adminChangeParametersDto.getDateOfBirth());
        admin.setContactNo(adminChangeParametersDto.getContactNo());
        admin.setRole(roleRepository.findRoleByName("ROLE_CLIENT").get());
        return admin;
    }

    public FullAdminDto adminToFullAdminDto(Admin admin)
    {
        FullAdminDto fullAdminDto=new FullAdminDto();
        fullAdminDto.setEmail(admin.getEmail());
        fullAdminDto.setFirstName(admin.getFirstName());
        fullAdminDto.setLastName(admin.getLastName());
        fullAdminDto.setUsername(admin.getUsername());
        fullAdminDto.setPassword(admin.getPassword());
        fullAdminDto.setDateOfBirth(admin.getDateOfBirth());
        fullAdminDto.setContactNo(admin.getContactNo());

        return fullAdminDto;
    }

    public  AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setEmail(admin.getEmail());
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setUsername(admin.getUsername());
        return adminDto;
    }
}
