package com.eren.parkinghouse.service;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.ParkingHouse;
import com.eren.parkinghouse.repository.ParkingHouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by esimsek on 10/5/2016.
 */
@Service
@Transactional
public class ParkingHouseService {

    private final Logger log = LoggerFactory.getLogger(ParkingHouseService.class);

    @Autowired
    private ParkingHouseRepository parkingHouseRepository;

    public ParkingHouse findById(Long id){
        return parkingHouseRepository.findOne(id);
    }

    public Iterable<ParkingHouse> findAll() {
        return parkingHouseRepository.findAll();
    }

    public ParkingHouse save(ParkingHouse parkingHouse) {
        return parkingHouseRepository.save(parkingHouse);
    }

    public ParkingHouse update(ParkingHouse parkingHouse) {
        return parkingHouseRepository.save(parkingHouse);
    }

    public void delete(ParkingHouse parkingHouseHouse) {
        parkingHouseRepository.delete(parkingHouseHouse);
    }

}
