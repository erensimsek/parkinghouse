package com.eren.parkinghouse.repository;

import com.eren.parkinghouse.domain.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by esimsek on 10/5/2016.
 */
@RepositoryRestResource
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Collection<Parking> findByCustomerId(@Param("customerId") String customerId);

}
