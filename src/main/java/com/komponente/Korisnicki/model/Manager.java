package com.komponente.Korisnicki.model;;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Menager")
public class Manager extends User{
    private String nameOfCompany;
    private Date dateOfEmployment;
    private boolean forbidden;
    private String isActivate;


    public Manager() {
        super();
    }

    public String getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(String isActivate) {
        this.isActivate = isActivate;
    }

    public Manager(String email, String firstName, String lastName, String username, String password, Date dateOfBirth, String contactNo, Role role, String nameOfCompany, Date dateOfEmployment, boolean forbidden, String isActivate) {
        super(email, firstName, lastName, username, password, dateOfBirth, contactNo, role);
        this.nameOfCompany = nameOfCompany;
        this.dateOfEmployment = dateOfEmployment;
        this.forbidden = forbidden;
        this.isActivate = isActivate;
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

    public boolean isForbidden() {
        return forbidden;
    }

    public void setForbidden(boolean forbidden) {
        this.forbidden = forbidden;
    }
}
