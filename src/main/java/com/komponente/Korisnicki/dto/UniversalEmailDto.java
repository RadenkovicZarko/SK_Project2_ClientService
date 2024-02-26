package com.komponente.Korisnicki.dto;

import java.util.Date;

public class UniversalEmailDto {

    private String nameOfClass="";
    private String emailOfClient="";
    private String name="";
    private String lastName="";
    private String link="";
    private Long idVehicle=(long)0;
    private String model="";
    private String type="";
    private Date from=null;
    private Date to=null;
    private String emailOfManager="";
    private String password;

    public UniversalEmailDto() {
    }

    public UniversalEmailDto(String nameOfClass, String emailOfClient, String name, String lastName, String link, Long idVehicle, String model, String type, Date from, Date to, String emailOfManager, String password) {
        this.nameOfClass = nameOfClass;
        this.emailOfClient = emailOfClient;
        this.name = name;
        this.lastName = lastName;
        this.link = link;
        this.idVehicle = idVehicle;
        this.model = model;
        this.type = type;
        this.from = from;
        this.to = to;
        this.emailOfManager = emailOfManager;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailOfClient() {
        return emailOfClient;
    }

    public void setEmailOfClient(String emailOfClient) {
        this.emailOfClient = emailOfClient;
    }

    public String getNameOfClass() {
        return nameOfClass;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getEmailOfManager() {
        return emailOfManager;
    }

    public void setEmailOfManager(String emailOfManager) {
        this.emailOfManager = emailOfManager;
    }
}
