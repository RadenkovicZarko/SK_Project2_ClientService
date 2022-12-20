package com.komponente.Korisnicki.service;


import com.komponente.Korisnicki.dto.ManagerCreateDto;
import com.komponente.Korisnicki.dto.ManagerDto;
import com.komponente.Korisnicki.dto.TokenRequestDto;
import com.komponente.Korisnicki.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);
    ManagerDto add(ManagerCreateDto managerCreateDto);
}
