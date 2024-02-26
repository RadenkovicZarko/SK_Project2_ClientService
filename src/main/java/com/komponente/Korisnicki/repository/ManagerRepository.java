package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);
    Optional<Manager> findByIsActivate(String is_activate);

    @Modifying
    @Query(value = "update user u set u.first_name = ?1, u.last_name = ?2,u.email = ?3,u.date_of_birth = ?4,u.contact_no = ?5,u.date_of_employment = ?6, u.username = ?7 where u.id = ?8", nativeQuery = true)
    void setManagerParametersById(String firstname, String lastname, String email, Date dateOfBirth, String contactNo, Date dateOfEmployment, String username, String userId);
}
