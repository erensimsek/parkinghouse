package com.eren.parkinghouse.service;

import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.enums.ParkingTimeType;
import com.eren.parkinghouse.repository.ParkingRepository;
import com.eren.parkinghouse.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

/**
 * Created by esimsek on 10/5/2016.
 */
@Service
@Transactional
public class ParkingService {

    private final Logger log = LoggerFactory.getLogger(ParkingService.class);

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private InvoiceDetailService ınvoiceDetailService;

    public Iterable<Parking> findAll(){
        return parkingRepository.findAll();
    }

    public Parking save(Parking parking){
        if(parking.getParkingEndDate() != null){

            parking.setHalfHourCountAm(TimeUtil.CalculateAMHalfHours(parking.getParkingStartDate(), parking.getParkingEndDate()));
            parking.setHalfHourCountPm(TimeUtil.CalculatePMHalfHours(parking.getParkingStartDate(), parking.getParkingEndDate()));

            parking = parkingRepository.save(parking);
            ınvoiceDetailService.createInvoice(parking);
        } else {
            parking = parkingRepository.save(parking);
        }
        return parking;
    }

    public Parking update(Parking parking){
        return parkingRepository.save(parking);
    }

    public void delete(Parking parking){
        parkingRepository.delete(parking);
    }

}
