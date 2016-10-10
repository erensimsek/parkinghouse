package com.eren.parkinghouse.view;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.domain.ParkingHouse;
import com.eren.parkinghouse.service.CustomerService;
import com.eren.parkinghouse.service.ParkingHouseService;
import com.eren.parkinghouse.service.ParkingService;
import com.eren.parkinghouse.util.JsfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by esimsek on 10/6/2016.
 */

@Controller
public class ParkingManagedBean {

    private final Logger log = LoggerFactory.getLogger(ParkingManagedBean.class);

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ParkingHouseService parkingHouseService;


    private List<ParkingHouse> parkingHouseList;

    private List<Customer> customerList;
    private List<Parking> parkingList;
    private Parking parking = new Parking();


    public List<Parking> getParkingList() {
        return (List)parkingService.findAll();
    }

    public List<Customer> getCustomerList() {
        return (List)customerService.findAll();
    }

    public void saveParking() {
        try {
            parking.setCustomer(customerService.findById(parking.getCustomer().getId()));
            Parking savedParking =  parkingService.save(parking);
            parking = new Parking();
            JsfUtil.addSuccessMessage("Parking saved successfully! ID : "+savedParking.getId());
        } catch (Exception e){
            JsfUtil.addErrorMessage("Error : "+e.getMessage());
            log.error(e.getMessage(),e);
        }
    }

    public void deleteParking(Parking parking) {
        parkingService.delete(parking);
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public ParkingService getParkingService() {
        return parkingService;
    }

    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void setParkingList(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<ParkingHouse> getParkingHouseList() {
        return (List)parkingHouseService.findAll();
    }

    public void setParkingHouseList(List<ParkingHouse> parkingHouseList) {
        this.parkingHouseList = parkingHouseList;
    }
}
