package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByUsernameAndPassword(String username, String password);


    //METODA RADI  UPDATE imena prezimena ZA ODREDJENI ID, VIDETI KAKO DA SE NAPRAVI UPDATE SAMO ZA ELEMENTE KOJI NISU NULL
    @Modifying
    @Query(value = "update user u set u.first_name = ?1, u.last_name = ?2,u.email = ?3,u.date_of_birth = ?4,u.contact_no = ?5,u.passport_no = ?6, u.username = ?7 where u.id = ?8", nativeQuery = true)
    void setClientParametersById(String firstname, String lastname, String email,  Date dateOfBirth, String contactNo, String passportNo,String username, String userId);


    @Modifying
    @Query(value = "update user u set u.number_of_renting_days = ?1 where u.id = ?2", nativeQuery = true)
    void setClientNumberOfRentingDaysById(int numberOfRentingDays,  String userId);


    @Modifying
    @Query(value = "update user u set u.forbidden = ?1 where u.id = ?2", nativeQuery = true)
    void setClientForbiddenById(String forbidden,  String userId);


    Optional<Client> findByIsActivate(String is_activate);
}
