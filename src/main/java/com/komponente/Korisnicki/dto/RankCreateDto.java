package com.komponente.Korisnicki.dto;

public class RankCreateDto {

    private Integer minNumberOfRentingDays;
    private Integer maxNumberOfRentingDays;
    private Integer discount;



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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
