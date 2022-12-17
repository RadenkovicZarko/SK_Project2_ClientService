package com.komponente.Korisnicki.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.Date;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{
    private Long passportNo;
    private int numberOfRentingDays;

    public Client() {
        super();
    }

    public Client(Long passportNo, int numberOfRentingDays,  String email, String firstName, String lastName,
                  String username, String password, Date dateOfBirth, String contactNo, Role role) {
        super(email,firstName,lastName,username, password, dateOfBirth,contactNo,role);
        this.passportNo = passportNo;
        this.numberOfRentingDays = numberOfRentingDays;
    }

    public Long getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(Long passportNo) {
        this.passportNo = passportNo;
    }

    public int getNumberOfRentingDays() {
        return numberOfRentingDays;
    }

    public void setNumberOfRentingDays(int numberOfRentingDays) {
        this.numberOfRentingDays = numberOfRentingDays;
    }
}
