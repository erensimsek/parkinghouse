package com.eren.parkinghouse.domain;

import com.eren.parkinghouse.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by esimsek on 10/5/2016.
 */

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Length(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Length(max = 50)
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Email
    @Length(max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Parking> parkings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Set<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(Set<Parking> parkings) {
        this.parkings = parkings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        return !(getId() != null ? !getId().equals(customer.getId()) : customer.getId() != null);

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
