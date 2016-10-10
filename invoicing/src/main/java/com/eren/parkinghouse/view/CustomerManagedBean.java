package com.eren.parkinghouse.view;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.domain.Invoice;
import com.eren.parkinghouse.domain.InvoiceDetail;
import com.eren.parkinghouse.enums.CustomerType;
import com.eren.parkinghouse.service.CustomerService;
import com.eren.parkinghouse.service.InvoiceDetailService;
import com.eren.parkinghouse.service.InvoiceService;
import com.eren.parkinghouse.util.JsfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by esimsek on 10/6/2016.
 */

@Controller
public class CustomerManagedBean {

    private final Logger log = LoggerFactory.getLogger(CustomerManagedBean.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @Autowired
    private InvoiceService invoiceService;

    private List<CustomerType> customerTypeList;
    private List<Customer> customerList;
    private List<InvoiceDetail> invoiceDetailList;
    private List<InvoiceDetail> preInvoiceDetailList;
    private List<Invoice> invoiceList;
    private Customer customer = new Customer();

    private BigDecimal montlyFeeTemp;
    private BigDecimal totalFeeTemp;

    @PostConstruct
    public void init() {
        customerList = (List)customerService.findAll();
        customerTypeList = Arrays.asList(CustomerType.values());
    }

    public List<Customer> getCustomerList() {
        return (List)customerService.findAll();
    }

    public void findByInvoiceCustomerId(Customer customer){
        invoiceDetailList = (List)invoiceDetailService.findByInvoiceCustomerId(customer.getId());
        montlyFeeTemp=new BigDecimal(0);
        totalFeeTemp=new BigDecimal(0);
        for (int i = 0; i < invoiceDetailList.size(); i++) {
            InvoiceDetail d = invoiceDetailList.get(i);
            totalFeeTemp = totalFeeTemp.add(d.getParkingFee());
        }
        invoiceList = (List)invoiceService.findByCustomerId(customer.getId());
        montlyFeeTemp = customer.getCustomerType().getMontlyFee()==null?new BigDecimal(0):customer.getCustomerType().getMontlyFee();
        totalFeeTemp = invoiceService.getFeeByCustomerType(customer, totalFeeTemp.add(montlyFeeTemp));
    }

    public void generateInvoiceForCustomer(Customer customer){
        try {
            List<Invoice> list =  invoiceService.generateInvoiceOfCustomer(customer);
            JsfUtil.addSuccessMessage(list.size() + " invoice(s) Generated for customer ID : " + customer.getId() + " successfully!");
        } catch (Exception e){
            JsfUtil.addErrorMessage("Error : "+e.getMessage());
            log.error(e.getMessage(), e);
        }

    }

    public void saveCustomer() {
        try {
            Customer savedCustomer = customerService.save(customer);
            customer = new Customer();
            JsfUtil.addSuccessMessage("Customer saved successfully! ID : " + savedCustomer.getId());
        } catch (Exception e){
            JsfUtil.addErrorMessage("Error : "+e.getMessage());
            log.error(e.getMessage(), e);
        }
    }

    public void findByInvoiceDetailsByInvoice(Invoice invoice){
        preInvoiceDetailList = (List)invoiceDetailService.findByInvoiceId(invoice.getId());
    }

    public void deleteCustomer(Customer customer) {
        customerService.delete(customer);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<CustomerType> getCustomerTypeList() {
        return customerTypeList;
    }

    public void setCustomerTypeList(List<CustomerType> customerTypeList) {
        this.customerTypeList = customerTypeList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<InvoiceDetail> getInvoiceDetailList() {
        return invoiceDetailList;
    }

    public void setInvoiceDetailList(List<InvoiceDetail> invoiceDetailList) {
        this.invoiceDetailList = invoiceDetailList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public List<InvoiceDetail> getPreInvoiceDetailList() {
        return preInvoiceDetailList;
    }

    public void setPreInvoiceDetailList(List<InvoiceDetail> preInvoiceDetailList) {
        this.preInvoiceDetailList = preInvoiceDetailList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public BigDecimal getTotalFeeTemp() {
        return totalFeeTemp;
    }

    public void setTotalFeeTemp(BigDecimal totalFeeTemp) {
        this.totalFeeTemp = totalFeeTemp;
    }

    public BigDecimal getMontlyFeeTemp() {
        return montlyFeeTemp;
    }

    public void setMontlyFeeTemp(BigDecimal montlyFeeTemp) {
        this.montlyFeeTemp = montlyFeeTemp;
    }
}
