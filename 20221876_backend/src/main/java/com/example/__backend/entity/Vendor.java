package com.example.__backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore; // To prevent circular references during JSON serialization
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a Vendor in the system.
 * A Vendor is associated with multiple Tickets that they offer for sale.
 */
@Entity // Marks this class as a JPA entity to be mapped to a database table
public class Vendor {

    @Id // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the value of this field is automatically generated
    private Long id;

    private String vendorName; // The name of the vendor
    private String contactInfo; // Contact information of the vendor (e.g., email )

    @JsonIgnore // Prevents the 'tickets' list from being serialized to JSON to avoid infinite recursion
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true) // Defines a one-to-many relationship with the Ticket entity
    private List<Ticket> tickets; // List of tickets associated with the vendor

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
