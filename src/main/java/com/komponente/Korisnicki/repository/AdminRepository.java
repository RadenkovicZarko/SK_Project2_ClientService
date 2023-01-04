package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.model.Admin;
import com.komponente.Korisnicki.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Modifying
    @Query(value = "update user u set u.first_name = ?1, u.last_name = ?2,u.email = ?3,u.date_of_birth = ?4,u.contact_no = ?5, u.username = ?6 where u.id = ?7", nativeQuery = true)
    void setAdminParametersById(String firstname, String lastname, String email, Date dateOfBirth, String contactNo,  String username, String userId);


}
