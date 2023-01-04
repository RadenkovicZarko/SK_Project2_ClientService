package com.komponente.Korisnicki.model;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{
    private Long passportNo;
    private int numberOfRentingDays;
    private boolean forbidden;
    private String isActivate;

    public Client() {
        super();
    }

    public Client(String email, String firstName, String lastName, String username, String password, Date dateOfBirth, String contactNo, Role role, Long passportNo, int numberOfRentingDays, boolean forbidden, String isActivate) {
        super(email, firstName, lastName, username, password, dateOfBirth, contactNo, role);
        this.passportNo = passportNo;
        this.numberOfRentingDays = numberOfRentingDays;
        this.forbidden = forbidden;
        this.isActivate = isActivate;
    }

    public Client(Long passportNo, int numberOfRentingDays, boolean forbidden, String email, String firstName, String lastName,
                  String username, String password, Date dateOfBirth, String contactNo, Role role) {
        super(email,firstName,lastName,username, password, dateOfBirth,contactNo,role);
        this.passportNo = passportNo;
        this.numberOfRentingDays = numberOfRentingDays;
        this.forbidden=forbidden;
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

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }

    public String getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(String isActivate) {
        this.isActivate = isActivate;
    }

    @Override
    public String toString() {
        return "Client{" +super.toString()+
                "passportNo=" + passportNo +
                ", numberOfRentingDays=" + numberOfRentingDays +
                ", forbidden=" + forbidden +
                '}';
    }


}
