package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.*;


public interface AdminService {

    RankDto add(RankCreateDto rankCreateDto);
    UserDto updateForbiden(ClientForbidenDto clientForbidenDto);
    FullAdminDto findByIdToUpdate(SearchUserDto id);
    AdminDto update(AdminChangeParametersDto adminChangeParametersDto);
}
