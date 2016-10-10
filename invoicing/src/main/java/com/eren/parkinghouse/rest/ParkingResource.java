package com.eren.parkinghouse.rest;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.domain.ParkingHouse;
import com.eren.parkinghouse.dto.ParkingDTO;
import com.eren.parkinghouse.service.CustomerService;
import com.eren.parkinghouse.service.ParkingHouseService;
import com.eren.parkinghouse.service.ParkingService;
import com.eren.parkinghouse.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


/**
 * Created by esimsek on 10/5/2016.
 */

@RestController
@RequestMapping("/apiv2")
public class ParkingResource {

    private final Logger log = LoggerFactory.getLogger(ParkingResource.class);

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ParkingHouseService parkingHouseService;


    /**
     * GET  /parkings : get all the parkings records.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of parking in body
     */
    @RequestMapping(value = "/parkings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Parking> getAllParkings() {
        List<Parking> parkingList = (List<Parking>) parkingService.findAll();
        return parkingList;
    }

    /**
     * POST  /create-parking : Create a new parking.
     *
     * @param parkingDTO the parking to create
     * @return the ResponseEntity with status 201 (Created) and with body the new parking, or with status 400 (Bad Request) if the parking has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/create-parking-to-customer",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Parking> createParking(@Valid @RequestBody ParkingDTO parkingDTO) throws URISyntaxException {
        log.debug("REST request to save Parking : {}", parkingDTO);
        if(parkingDTO.getCustomerId()==null){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("parking", "customer id null", "A new parking needs customer info")).body(null);
        }
        if(parkingDTO.getParkingHouseId()==null){
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("parking", "parking house id null", "A new parking needs parking house info")).body(null);
        }
        Customer c = customerService.findById(parkingDTO.getCustomerId());
        ParkingHouse pH = parkingHouseService.findById(parkingDTO.getParkingHouseId());
        Parking parking = new Parking();
        parking.setParkingStartDate(parkingDTO.getParkingStartDate());
        parking.setParkingEndDate(parkingDTO.getParkingEndDate());
        parking.setCustomer(c);
        parking.setParkingHouse(pH);

        Parking result = parkingService.save(parking);
        return ResponseEntity.created(new URI("/apiv2/create-parking-to-customer/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("parking", result.getId().toString()))
                .body(result);
    }

}
