package com.komponente.Korisnicki.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String email, String firstName, String lastName,
                   String username, String password, Date dateOfBirth, String contactNo, Role role) {
        super(email,firstName,lastName,username, password, dateOfBirth,contactNo,role);
    }
}
