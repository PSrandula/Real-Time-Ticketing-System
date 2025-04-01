package com.example.__backend.Repository;

import com.example.__backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // JpaRepository provides basic CRUD operations such as save(), findAll(), findById(), delete(), etc.
    // You can add custom query methods here if needed for specific queries related to customers.
}
