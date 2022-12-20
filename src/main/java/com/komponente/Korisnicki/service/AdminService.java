package com.komponente.Korisnicki.service;

import com.komponente.Korisnicki.dto.DiscountDto;
import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.repository.RankRepository;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

public interface AdminService {
    DiscountDto findDiscount(Long id);

}
