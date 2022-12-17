package com.komponente.Korisnicki.model;;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Menager")
public class Menager extends User{
    private String nameOfCompany;
    private Date dateOfEmployment;


    public Menager() {
        super();
    }

    public Menager(String nameOfCompany, Date dateOfEmployment, String email, String firstName, String lastName,
                  String username, String password, Date dateOfBirth, String contactNo, Role role) {
        super(email,firstName,lastName,username, password, dateOfBirth,contactNo,role);
        this.nameOfCompany=nameOfCompany;
        this.dateOfEmployment=dateOfEmployment;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }
}
