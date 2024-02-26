package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.dto.UserDto;
import com.komponente.Korisnicki.model.Manager;
import com.komponente.Korisnicki.model.User;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailAndPassword(String username, String password);



}
