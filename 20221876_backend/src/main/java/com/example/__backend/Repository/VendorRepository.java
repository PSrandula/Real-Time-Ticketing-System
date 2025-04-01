package com.example.__backend.Repository;

import com.example.__backend.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    // No additional methods are required as JpaRepository provides basic CRUD operations
    // You can add custom query methods here if needed
}
