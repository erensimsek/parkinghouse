package com.eren.parkinghouse.service;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.Invoice;
import com.eren.parkinghouse.domain.InvoiceDetail;
import com.eren.parkinghouse.repository.InvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by esimsek on 10/7/2016.
 */
@Service
@Transactional
public class InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice findById(Long id) {
        return invoiceRepository.findOne(id);
    }

    public Iterable<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice update(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public Iterable<Invoice> findByCustomerId(Long id) {
        return invoiceRepository.findByCustomerId(id);
    }

    public Invoice findOneByMonthAndYearAndCustomer(Integer monthAndYear, Long customerId) {
        return invoiceRepository.findOneByMonthAndYearAndCustomer(monthAndYear, customerId);
    }

    public List<Invoice> generateInvoiceOfCustomer(Customer customer) {

        Calendar reqTime = Calendar.getInstance();
        Calendar parkingTimeTmp = Calendar.getInstance();
        Integer monthAndYearTmp = null;

        Long totalUsageHalfHours = new Long(0);
        BigDecimal montlyFee = customer.getCustomerType().getMontlyFee()==null?new BigDecimal(0):customer.getCustomerType().getMontlyFee();
        BigDecimal totalParkingFee = new BigDecimal(0);
        BigDecimal totalInvoice;
        List<InvoiceDetail> detailList = (List) invoiceDetailService.findByInvoiceCustomerId(customer.getId());
        List<InvoiceDetail> detailListTmp = new ArrayList<InvoiceDetail>();

        List<Invoice> resultInvoiceList = new ArrayList<Invoice>();
        Invoice invTemp;

        for (int i = 0; i < detailList.size(); i++) {
            InvoiceDetail invoiceDetail = detailList.get(i);
            parkingTimeTmp.setTime(invoiceDetail.getParking().getParkingEndDate());
            monthAndYearTmp = new Integer("" + (parkingTimeTmp.get(Calendar.MONTH) + 1) + parkingTimeTmp.get(Calendar.YEAR));

            if (reqTime.get(Calendar.MONTH) > parkingTimeTmp.get(Calendar.MONTH)) {

                invTemp = findOneByMonthAndYearAndCustomer(monthAndYearTmp, customer.getId());

                if (invTemp == null) {
                    invTemp = new Invoice();
                    invTemp.setCustomer(customer);
                    invTemp.setMontlyFee(customer.getCustomerType().getMontlyFee());
                    invTemp.setMonthAndYear(monthAndYearTmp);
                    resultInvoiceList.add(invTemp);
                } else {
                    totalInvoice = invTemp.getTotalInvoice();
                    totalParkingFee = invTemp.getTotalParkingFee();
                    totalUsageHalfHours = invTemp.getTotalUsageHalfHours();
                }

                detailListTmp.add(invoiceDetail);
                totalUsageHalfHours += (invoiceDetail.getParking().getHalfHourCountAm()+invoiceDetail.getParking().getHalfHourCountPm());
                totalParkingFee = totalParkingFee.add(invoiceDetail.getParkingFee());
                invTemp.setTotalUsageHalfHours(totalUsageHalfHours);
                invTemp.setTotalParkingFee(totalParkingFee);
                totalInvoice = getFeeByCustomerType(customer, totalParkingFee.add(montlyFee));
                invTemp.setTotalInvoice(totalInvoice);
                invTemp.getInvoiceDetails().add(invoiceDetail);
                invoiceRepository.save(invTemp);
                invoiceDetail.setInvoice(invTemp);
                invoiceDetailService.update(invoiceDetail);
            }

        }

        return resultInvoiceList;
    }

    public BigDecimal getFeeByCustomerType(Customer customer, BigDecimal totalInvoice) {
        if (customer.getCustomerType().getMontlyFee() != null  &&
                totalInvoice.compareTo(customer.getCustomerType().getMontlyFee()) == 1) {
            totalInvoice = customer.getCustomerType().getMaxFee();
        }
        return totalInvoice;
    }
}
