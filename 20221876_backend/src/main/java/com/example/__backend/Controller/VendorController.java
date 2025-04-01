package com.example.__backend.Controller;

import com.example.__backend.Repository.TicketRepository;
import com.example.__backend.Repository.VendorRepository;
import com.example.__backend.entity.Ticket;
import com.example.__backend.entity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendors") // Base URL for vendor-related API endpoints
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository; // Injecting VendorRepository to interact with vendor data

    @Autowired
    private TicketRepository ticketRepository;  // Injecting TicketRepository to interact with ticket data

    /**
     * Adds a new vendor.
     *
     * @param vendor The vendor to be added.
     * @return The added vendor.
     */
    @PostMapping
    public Vendor addVendor(@RequestBody Vendor vendor) {
        // Saving the new vendor to the database using the vendor repository
        return vendorRepository.save(vendor);
    }
    /**
     * Retrieves all vendors.
     *
     * @return A list of all vendors.
     */
    @GetMapping
    public List<Vendor> getAllVendors() {
        // Fetching and returning all vendors from the vendor repository
        return vendorRepository.findAll();
    }

    /**
     * Adds a ticket to a specific vendor.
     *
     * @param vendorID The ID of the vendor.
     * @param ticket   The ticket to be added to the vendor.
     * @return The updated ticket after being associated with the vendor.
     */
    @PostMapping("/{vendorID}/tickets")
    public Ticket addTicketToVendor(@PathVariable long vendorID, @RequestBody Ticket ticket){
        // Retrieving the vendor by ID from the vendor repository
        Optional<Vendor> vendor =vendorRepository.findById(vendorID);

        if (vendor.isPresent()){
            // Associating the ticket with the vendor
            ticket.setVendor(vendor.get());
            // Saving the updated ticket to the ticket repository
            return ticketRepository.save(ticket);
        }else {
            // Throwing an exception if the vendor is not found
            throw new RuntimeException("Vendor not found");
        }
    }
    /**
     * Retrieves all tickets associated with a specific vendor.
     *
     * @param vendorId The ID of the vendor.
     * @return A list of tickets associated with the vendor.
     */
    @GetMapping("/{vendorId}/tickets")
    public List<Ticket> getTicketsByVendor(@PathVariable Long vendorId) {
        // Retrieving the vendor by ID from the vendor repository
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);
        if (vendorOptional.isPresent()) {
            return vendorOptional.get().getTickets(); // Fetch tickets from Vendor entity
        } else {
            // Throwing an exception if the vendor is not found
            throw new RuntimeException("Vendor not found with ID: " + vendorId);
        }
    }

}
