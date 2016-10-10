package com.eren.parkinghouse.view;

import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.domain.ParkingHouse;
import com.eren.parkinghouse.service.ParkingHouseService;
import com.eren.parkinghouse.util.JsfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by esimsek on 10/6/2016.
 */

@Controller
public class ParkingHouseManagedBean {

    private final Logger log = LoggerFactory.getLogger(ParkingHouseManagedBean.class);

    @Autowired
    private ParkingHouseService parkingHouseService;


    private List<ParkingHouse> parkingHouseList;
    private ParkingHouse parkingHouse = new ParkingHouse();

    public void saveParking() {
        try {
            ParkingHouse savedParking =  parkingHouseService.save(parkingHouse);
            parkingHouse = new ParkingHouse();
            JsfUtil.addSuccessMessage("Parking House saved successfully! ID : " + savedParking.getId());
        } catch (Exception e){
            JsfUtil.addErrorMessage("Error : "+e.getMessage());
            log.error(e.getMessage(),e);
        }
    }
    public List<ParkingHouse> getParkingHouseList() {
        return (List) parkingHouseService.findAll();
    }

    public void deleteParkingHouse(ParkingHouse parkingHouse) {
        parkingHouseService.delete(parkingHouse);
    }

    public void setParkingHouse(ParkingHouse parkingHouse) {
        this.parkingHouse = parkingHouse;
    }

    public ParkingHouse getParkingHouse() {
        return parkingHouse;
    }

    public void setParkingHouseList(List<ParkingHouse> parkingHouseList) {
        this.parkingHouseList = parkingHouseList;
    }



}