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
    @Query(value = "update Client u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3", nativeQuery = true)
    Optional<Client> setUserInfoById(String firstname, String lastname, Integer userId);


}
