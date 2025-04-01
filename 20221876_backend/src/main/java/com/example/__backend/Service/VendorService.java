package com.example.__backend.Service;

import com.example.__backend.Repository.VendorRepository;
import com.example.__backend.entity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Vendor entities.
 * Provides methods to add a vendor and retrieve a vendor by ID.
 */
@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor addvendor(Vendor vendor){
        return vendorRepository.save(vendor); // Save the vendor to the database and return the saved entity
    }
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElse(null); // Fetch the vendor by ID, return null if not found
    }
}
