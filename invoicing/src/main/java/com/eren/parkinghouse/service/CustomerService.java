package com.eren.parkinghouse.service;

import com.eren.parkinghouse.domain.Customer;
import com.eren.parkinghouse.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by esimsek on 10/6/2016.
 */
@Service
@Transactional
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(ParkingService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(Long id){
        return customerRepository.findOne(id);
    }

    public Iterable<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }

    public void delete(Customer customer){
        customerRepository.delete(customer);
    }
}
