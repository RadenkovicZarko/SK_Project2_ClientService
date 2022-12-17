package com.komponente.Korisnicki.model;;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Menager")
public class Manager extends User{
    private String nameOfCompany;
    private String dateOfEmployment;
    private boolean forbidden;


    public Manager() {
        super();
    }

    public Manager(String nameOfCompany, String dateOfEmployment,boolean forbidden, String email, String firstName, String lastName,
                   String username, String password, String dateOfBirth, String contactNo, Role role) {
        super(email,firstName,lastName,username, password, dateOfBirth,contactNo,role);
        this.nameOfCompany=nameOfCompany;
        this.dateOfEmployment=dateOfEmployment;
        this.forbidden=forbidden;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }
}
