package com.example.__backend.Controller;

import com.example.__backend.Repository.TicketRepository;
import com.example.__backend.Service.TicketService;
import com.example.__backend.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")  // The base URL for ticket-related API endpoints
public class TicketController {

    @Autowired
    private TicketService ticketService; // Injecting the TicketService to handle business logic
    @Autowired
    private TicketRepository ticketRepository; // Injecting the TicketRepository to interact with the database

    /**
     * Endpoint to create a new ticket.
     * @param ticket The ticket object passed in the request body.
     * @return The created ticket.
     */
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        // Calling the TicketService to add the new ticket
        return ticketService.addTicket(ticket);
    }

    /**
     * Endpoint to retrieve all tickets.
     * @return A list of all tickets.
     */
    @GetMapping
    public List<Ticket> getAllTickets(){
        // Calling the TicketService to fetch all tickets
        return ticketService.getAllTickets();
    }

    /**
     * Endpoint to delete a ticket by its ID.
     * @param id The ID of the ticket to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        // Deleting the ticket from the repository using the ID provided in the URL path
        ticketRepository.deleteById(id);
    }
}
