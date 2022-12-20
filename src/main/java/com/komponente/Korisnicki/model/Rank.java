package com.komponente.Korisnicki.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer minNumberOfRentingDays;
    private Integer maxNumberOfRentingDays;
    private Integer discount;


    public Rank(Long id, Integer minNumberOfRentingDays, Integer maxNumberOfRentingDays, Integer discount) {
        this.id = id;
        this.minNumberOfRentingDays = minNumberOfRentingDays;
        this.maxNumberOfRentingDays = maxNumberOfRentingDays;
        this.discount = discount;
    }

    public Integer getMinNumberOfRentingDays() {
        return minNumberOfRentingDays;
    }

    public void setMinNumberOfRentingDays(Integer minNumberOfRentingDays) {
        this.minNumberOfRentingDays = minNumberOfRentingDays;
    }

    public Integer getMaxNumberOfRentingDays() {
        return maxNumberOfRentingDays;
    }

    public void setMaxNumberOfRentingDays(Integer maxNumberOfRentingDays) {
        this.maxNumberOfRentingDays = maxNumberOfRentingDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
