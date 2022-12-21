package com.komponente.Korisnicki.repository;

import com.komponente.Korisnicki.model.Client;
import com.komponente.Korisnicki.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByUsernameAndPassword(String username, String password);


    //METODA RADI  UPDATE imena prezimena ZA ODREDJENI ID, VIDETI KAKO DA SE NAPRAVI UPDATE SAMO ZA ELEMENTE KOJI NISU NULL
    @Modifying
    @Query(value = "update user u set u.first_name = ?1 where u.id = ?2", nativeQuery = true)
    void setClientFirstNameById(String firstname,  String userId);

    @Modifying
    @Query(value = "update user u set u.last_name = ?1 where u.id = ?2", nativeQuery = true)
    void setClientLastNameById(String lastname,  String userId);

    @Modifying
    @Query(value = "update user u set u.email = ?1 where u.id = ?2", nativeQuery = true)
    void setClientEmailById(String email,  String userId);

    @Modifying
    @Query(value = "update user u set u.password = ?1 where u.id = ?2", nativeQuery = true)
    void setClientPasswordById(String password,  String userId);

    @Modifying
    @Query(value = "update user u set u.date_of_birth = ?1 where u.id = ?2", nativeQuery = true)
    void setClientDateOfBirthById(String dateOfBirth,  String userId);

    @Modifying
    @Query(value = "update user u set u.contact_no = ?1 where u.id = ?2", nativeQuery = true)
    void setClientContactNoById(String contactNo,  String userId);

    @Modifying
    @Query(value = "update user u set u.passport_no = ?1 where u.id = ?2", nativeQuery = true)
    void setClientPassportNoById(String passportNo,  String userId);

    @Modifying
    @Query(value = "update user u set u.number_of_renting_days = ?1 where u.id = ?2", nativeQuery = true)
    void setClientNumberOfRentingDaysById(String numberOfRentingDays,  String userId);


    @Modifying
    @Query(value = "update user u set u.forbidden = ?1 where u.id = ?2", nativeQuery = true)
    void setClientForbiddenById(String forbidden,  String userId);
}
