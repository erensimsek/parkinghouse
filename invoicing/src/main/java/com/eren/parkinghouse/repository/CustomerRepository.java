package com.eren.parkinghouse.repository;

import com.eren.parkinghouse.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by esimsek on 10/6/2016.
 */
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Collection<Customer> findByName(@Param("name") String name);

}
