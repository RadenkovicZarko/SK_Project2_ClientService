package com.komponente.Korisnicki.service;


import com.komponente.Korisnicki.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);
    ManagerDto add(ManagerCreateDto managerCreateDto);
    ManagerDto activateAccount(String activateString);
    ManagerDto update(ManagerChangeParametersDto managerChangeParametersDto);

    FullManagerDto findByIdToUpdate(SearchUserDto id);

}
