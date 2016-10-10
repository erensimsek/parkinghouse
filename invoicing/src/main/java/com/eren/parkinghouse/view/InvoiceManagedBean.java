package com.eren.parkinghouse.view;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.Invoice;
import com.eren.parkinghouse.domain.InvoiceDetail;
import com.eren.parkinghouse.domain.Parking;
import com.eren.parkinghouse.service.CustomerService;
import com.eren.parkinghouse.service.InvoiceDetailService;
import com.eren.parkinghouse.service.InvoiceService;
import com.eren.parkinghouse.service.ParkingService;
import com.eren.parkinghouse.util.JsfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by esimsek on 10/6/2016.
 */

@Controller
public class InvoiceManagedBean {

    private final Logger log = LoggerFactory.getLogger(InvoiceManagedBean.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    private List<Invoice> invoiceList;
    private List<InvoiceDetail> invoiceDetailList;

    public List<Invoice> getInvoiceList() {
        invoiceList = (List)invoiceService.findAll();
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<InvoiceDetail> getInvoiceDetailList() {
        return invoiceDetailList;
    }

    public void setInvoiceDetailList(List<InvoiceDetail> invoiceDetailList) {
        this.invoiceDetailList = invoiceDetailList;
    }
}
