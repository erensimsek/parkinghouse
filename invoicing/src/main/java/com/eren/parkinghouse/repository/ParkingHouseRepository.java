package com.eren.parkinghouse.repository;

import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.domain.ParkingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by esimsek on 10/5/2016.
 */
@RepositoryRestResource
public interface ParkingHouseRepository extends JpaRepository<ParkingHouse, Long> {

}
