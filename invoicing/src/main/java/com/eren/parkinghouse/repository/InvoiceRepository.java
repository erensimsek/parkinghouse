package com.eren.parkinghouse.repository;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * Created by esimsek on 10/7/2016.
 */
@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

    Collection<Invoice> findByCustomerId(@Param("id") Long id);

    @Query("select u from Invoice u where u.monthAndYear = ?1 and u.customer.id = ?2")
    Invoice findOneByMonthAndYearAndCustomer(@Param("monthAndYear") Integer monthAndYear,@Param("customerId")  Long customerId);
}
