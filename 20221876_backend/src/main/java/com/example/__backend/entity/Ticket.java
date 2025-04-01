package com.example.__backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity // Marks this class as a JPA entity to be mapped to a database table
public class Ticket {

    @Id // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies that the value of this field is automatically generated
    private Long id;

    private String ticketName; // The name of the ticket (e.g., concert, event)
    private double ticketPrice; // The price of the ticket
    private int ticketCount; // The available number of tickets

    @ManyToOne // Defines a many-to-one relationship with the Customer entity
    @JsonBackReference // Prevents infinite recursion during JSON serialization (to avoid the circular reference between Ticket and Customer)
    @JoinColumn(name = "customer_id") // Specifies the foreign key column in the Ticket table that references the Customer entity
    private Customer customer;

    @ManyToOne // Defines a many-to-one relationship with the Vendor entity
    @JoinColumn(name = "vendor_id", nullable = false) // Specifies the foreign key column in the Ticket table that references the Vendor entity
    private  Vendor vendor; // The vendor that offers the ticket

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
