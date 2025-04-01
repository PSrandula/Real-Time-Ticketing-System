package com.example.__backend.Service;

import com.example.__backend.Repository.TicketRepository;
import com.example.__backend.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Ticket entities.
 * Provides methods to add a ticket and retrieve all tickets.
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket addTicket(Ticket ticket){
        return ticketRepository.save(ticket); // Save the ticket to the database and return the saved entity
    }
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll(); // Return all tickets from the database
    }
}
