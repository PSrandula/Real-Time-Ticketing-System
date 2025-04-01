package com.example.__backend.Service;

import com.example.__backend.Repository.CustomerRepository;
import com.example.__backend.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Customer entities.
 * Provides methods to add a customer, retrieve all customers, and retrieve a customer by ID.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer); // Save customer to DB and return saved entity
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll(); // Return all customers
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null); // Return customer or null if not found
    }
}
