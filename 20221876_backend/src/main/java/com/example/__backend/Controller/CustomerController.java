package com.example.__backend.Controller;

import com.example.__backend.entity.Customer;
import com.example.__backend.entity.Ticket;
import com.example.__backend.Repository.TicketRepository;
import com.example.__backend.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing customers and their interactions with tickets.
 * Provides endpoints to add customers, retrieve customer data, purchase tickets, and retrieve tickets associated with a customer.
 */
@RestController
@RequestMapping("/api/customers") // Base URL for customer-related API endpoints
public class CustomerController {

    @Autowired
    private CustomerService customerService; // Injecting CustomerService for customer-related operations

    @Autowired
    private TicketRepository ticketRepository; // Injecting TicketRepository to interact with ticket data

    /**
     * Adds a new customer.
     *
     * @param customer The customer to be added.
     * @return The added customer.
     */
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        // Adding the customer using the customer service
        return customerService.addCustomer(customer);
    }

    /**
     * Retrieves all customers.
     *
     * @return A list of all customers.
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        // Fetching and returning all customers using the customer service
        return customerService.getAllCustomers();
    }

    /**
     * Allows a customer to purchase a ticket.
     *
     * @param customerId The ID of the customer.
     * @param ticketId   The ID of the ticket to be purchased.
     * @return The updated ticket after the purchase.
     */
    @PostMapping("/{customerId}/tickets/{ticketId}")
    public Ticket buyTicket(@PathVariable Long customerId, @PathVariable Long ticketId) {
        // Retrieving the customer by ID
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + customerId); // Throw exception if customer not found
        }

        // Retrieving the ticket by ID
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isEmpty()) {
            throw new RuntimeException("Ticket not found with ID: " + ticketId); // Throw exception if ticket not found
        }

        Ticket ticket = ticketOptional.get();

        // Check if tickets are available
        if (ticket.getTicketCount() <= 0) {
            throw new RuntimeException("Tickets are sold out for: " + ticket.getTicketName()); // Throw exception if no tickets are available
        }

        // Reduce ticket count and associate with the customer
        ticket.setTicketCount(ticket.getTicketCount() - 1);
        ticket.setCustomer(customer);
        return ticketRepository.save(ticket); // Save updated ticket to the database
    }

    /**
     * Retrieves all tickets associated with a specific customer.
     *
     * @param customerId The ID of the customer.
     * @return A list of tickets associated with the customer.
     */
    @GetMapping("/{customerId}/tickets")
    public List<Ticket> getTicketsByCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
        // Return the list of tickets associated with the customer
        return customer.getTickets();
    }
}
