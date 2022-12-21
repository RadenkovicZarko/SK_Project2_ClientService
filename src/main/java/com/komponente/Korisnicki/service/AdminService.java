package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.*;


public interface AdminService {

    RankDto add(RankDto rankCreateDto);
    ClientDto updateForbiden(ClientForbidenDto clientForbidenDto);
}
