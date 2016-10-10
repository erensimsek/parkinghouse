package com.eren.parkinghouse.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by esimsek on 10/7/2016.
 */
public class ParkingDTO {

    @NotNull
    private Long customerId;
    @NotNull
    private Long parkingHouseId;
    @NotNull
    private Date parkingStartDate;

    private Date parkingEndDate;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Long getParkingHouseId() {
        return parkingHouseId;
    }

    public void setParkingHouseId(Long parkingHouseId) {
        this.parkingHouseId = parkingHouseId;
    }
}
