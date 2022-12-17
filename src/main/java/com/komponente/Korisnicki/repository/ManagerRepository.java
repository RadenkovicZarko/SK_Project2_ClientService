package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);
}
