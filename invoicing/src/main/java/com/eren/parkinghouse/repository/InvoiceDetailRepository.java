package com.eren.parkinghouse.repository;

import com.eren.parkinghouse.domain.Invoice;
import com.eren.parkinghouse.domain.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.NamedQuery;
import java.util.Collection;

/**
 * Created by esimsek on 10/7/2016.
 */
@RepositoryRestResource
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long>{

    @Query("select u from InvoiceDetail u where u.parking.customer.id = ?1 and u.invoice is null")
    Collection<InvoiceDetail> findByInvoiceCustomerId(@Param("id") Long id);

    @Query
    InvoiceDetail findByParkingId(@Param("id") Long id);

    Collection<InvoiceDetail> findByInvoiceId(@Param("id") Long id);

}
