package com.komponente.Korisnicki.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minNumberOfRentingDays;
    private int maxNumberOfRentingDays;
    private int discount;

    public ClientRank() {
    }

    public ClientRank( int mini, int maksi, int popust) {
        this.minNumberOfRentingDays = mini;
        this.maxNumberOfRentingDays = maksi;
        this.discount = popust;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMini() {
        return minNumberOfRentingDays;
    }

    public void setMini(int mini) {
        this.minNumberOfRentingDays = mini;
    }

    public int getMaksi() {
        return maxNumberOfRentingDays;
    }

    public void setMaksi(int maksi) {
        this.maxNumberOfRentingDays = maksi;
    }

    public int getPopust() {
        return discount;
    }

    public void setPopust(int popust) {
        this.discount = popust;
    }
}
