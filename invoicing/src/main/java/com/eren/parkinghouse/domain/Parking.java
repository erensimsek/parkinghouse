package com.eren.parkinghouse.domain;

import com.eren.parkinghouse.enums.CustomerType;
import com.eren.parkinghouse.enums.ParkingTimeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by esimsek on 10/5/2016.
 */


@Entity
public class Parking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "parking_house_id")
    private ParkingHouse parkingHouse;

    @NotNull
    @Basic(optional = false)
    @Column(name = "parking_start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date parkingStartDate;

    @Column(name = "parking_end_date", nullable = true)
    @Basic(optional = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date parkingEndDate;

    @Column(name = "half_hour_count_am", nullable = true)
    private Long halfHourCountAm;

    @Column(name = "half_hour_count_pm", nullable = true)
    private Long halfHourCountPm;

    @Transient
    private ParkingTimeType parkingTimeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getParkingStartDate() {
        return parkingStartDate;
    }

    public void setParkingStartDate(Date parkingStartDate) {
        this.parkingStartDate = parkingStartDate;
    }

    public Date getParkingEndDate() {
        return parkingEndDate;
    }

    public void setParkingEndDate(Date parkingEndDate) {
        this.parkingEndDate = parkingEndDate;
    }

    public ParkingHouse getParkingHouse() {
        return parkingHouse;
    }

    public void setParkingHouse(ParkingHouse parkingHouse) {
        this.parkingHouse = parkingHouse;
    }

    public Long getHalfHourCountAm() {
        return halfHourCountAm;
    }

    public void setHalfHourCountAm(Long halfHourCountAm) {
        this.halfHourCountAm = halfHourCountAm;
    }

    public Long getHalfHourCountPm() {
        return halfHourCountPm;
    }

    public void setHalfHourCountPm(Long halfHourCountPm) {
        this.halfHourCountPm = halfHourCountPm;
    }

    public void setParkingTimeType(ParkingTimeType parkingTimeType) {
        this.parkingTimeType = parkingTimeType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parking)) return false;

        Parking parking = (Parking) o;

        return !(getId() != null ? !getId().equals(parking.getId()) : parking.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }


}
