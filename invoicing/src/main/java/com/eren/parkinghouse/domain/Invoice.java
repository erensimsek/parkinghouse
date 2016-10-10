package com.eren.parkinghouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by esimsek on 10/7/2016.
 */

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "month_and_year", nullable = false)
    private Integer monthAndYear;

    @Column(name = "total_usage_half_hours")
    private Long totalUsageHalfHours;

    @Column(name = "montly_fee")
    private BigDecimal montlyFee;

    @Column(name = "total_parking_fee")
    private BigDecimal totalParkingFee;

    @Column(name = "total_invoice", nullable = false)
    private BigDecimal totalInvoice;

    @OneToMany(mappedBy = "invoice")
    @JsonIgnore
    private Set<InvoiceDetail> invoiceDetails = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getMonthAndYear() {
        return monthAndYear;
    }

    public void setMonthAndYear(Integer monthAndYear) {
        this.monthAndYear = monthAndYear;
    }

    public Long getTotalUsageHalfHours() {
        return totalUsageHalfHours;
    }

    public void setTotalUsageHalfHours(Long totalUsageHalfHours) {
        this.totalUsageHalfHours = totalUsageHalfHours;
    }

    public BigDecimal getMontlyFee() {
        return montlyFee;
    }

    public void setMontlyFee(BigDecimal montlyFee) {
        this.montlyFee = montlyFee;
    }

    public BigDecimal getTotalParkingFee() {
        return totalParkingFee;
    }

    public void setTotalParkingFee(BigDecimal totalParkingFee) {
        this.totalParkingFee = totalParkingFee;
    }

    public BigDecimal getTotalInvoice() {
        return totalInvoice;
    }

    public void setTotalInvoice(BigDecimal totalInvoice) {
        this.totalInvoice = totalInvoice;
    }

    public Set<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }
}
