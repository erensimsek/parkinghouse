package com.eren.parkinghouse.service;

import com.eren.parkinghouse.domain.InvoiceDetail;
import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.enums.CustomerType;
import com.eren.parkinghouse.repository.InvoiceDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

/**
 * Created by esimsek on 10/7/2016.
 */
@Service
@Transactional
public class InvoiceDetailService {

    private final Logger log = LoggerFactory.getLogger(InvoiceDetailService.class);

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetail findById(Long id) {
        return invoiceDetailRepository.findOne(id);
    }

    public Iterable<InvoiceDetail> findByInvoiceId(Long id) {
        return invoiceDetailRepository.findByInvoiceId(id);
    }

    public Iterable<InvoiceDetail> findAll() {
        return invoiceDetailRepository.findAll();
    }

    public InvoiceDetail save(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public InvoiceDetail update(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    public void delete(InvoiceDetail invoiceDetail) {
        invoiceDetailRepository.delete(invoiceDetail);
    }

    public BigDecimal calculateParkingFee(Parking parking) {
        BigDecimal unitFeePM = new BigDecimal(0), unitFeeAM = new BigDecimal(0);

        if (parking.getCustomer().getCustomerType().equals(CustomerType.PREMIUM)) {
            unitFeeAM = CustomerType.PREMIUM.getParkingFeeAm();
            unitFeePM = CustomerType.PREMIUM.getParkingFeePm();
        } else {
            unitFeeAM = CustomerType.REGULAR.getParkingFeeAm();
            unitFeePM = CustomerType.REGULAR.getParkingFeePm();
        }

        unitFeeAM = unitFeeAM.multiply(new BigDecimal(parking.getHalfHourCountAm()));
        unitFeePM = unitFeePM.multiply(new BigDecimal(parking.getHalfHourCountPm()));

        return unitFeeAM.add(unitFeePM);
    }

    public InvoiceDetail createInvoice(Parking parking) {

        InvoiceDetail invoiceDetail = invoiceDetailRepository.findByParkingId(parking.getId());

        if (invoiceDetail == null || invoiceDetail.getId() == null) {
            invoiceDetail = new InvoiceDetail();
            invoiceDetail.setCustomerType(parking.getCustomer().getCustomerType());
            invoiceDetail.setParking(parking);
            invoiceDetail.setParkingFee(this.calculateParkingFee(parking));
            invoiceDetail = this.save(invoiceDetail);
        }

        return invoiceDetail;
    }

    public Iterable<InvoiceDetail> findByInvoiceCustomerId(Long id) {
        return this.invoiceDetailRepository.findByInvoiceCustomerId(id);
    }
}
