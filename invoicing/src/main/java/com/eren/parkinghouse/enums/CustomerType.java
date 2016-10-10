package com.eren.parkinghouse.enums;

import java.math.BigDecimal;

/**
 * Created by esimsek on 10/5/2016.
 */

public enum CustomerType {
    REGULAR(null,new BigDecimal(1.5),new BigDecimal(1),null),
    PREMIUM(new BigDecimal(20),new BigDecimal(1),new BigDecimal(0.75),new BigDecimal(300));

    private BigDecimal montlyFee;
    private BigDecimal parkingFeeAm; //Parking fee for every started half hour (from 7am till 7pm)
    private BigDecimal parkingFeePm;  //Parking fee for every started half hour (from 7pm till 7am)
    private BigDecimal maxFee;  //Maximum invoice

    CustomerType(BigDecimal montlyFee, BigDecimal parkingFeeAm, BigDecimal parkingFeePm,BigDecimal maxFee) {
        this.montlyFee = montlyFee;
        this.parkingFeeAm = parkingFeeAm;
        this.parkingFeePm = parkingFeePm;
        this.maxFee = maxFee;
    }

    public BigDecimal getMontlyFee() {
        return montlyFee;
    }

    public void setMontlyFee(BigDecimal montlyFee) {
        this.montlyFee = montlyFee;
    }

    public BigDecimal getParkingFeeAm() {
        return parkingFeeAm;
    }

    public void setParkingFeeAm(BigDecimal parkingFeeAm) {
        this.parkingFeeAm = parkingFeeAm;
    }

    public BigDecimal getParkingFeePm() {
        return parkingFeePm;
    }

    public void setParkingFeePm(BigDecimal parkingFeePm) {
        this.parkingFeePm = parkingFeePm;
    }

    public BigDecimal getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(BigDecimal maxFee) {
        this.maxFee = maxFee;
    }
}
