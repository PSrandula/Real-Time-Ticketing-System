package com.example.__backend.Repository;

import com.example.__backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // JpaRepository provides basic CRUD operations such as save(), findAll(), delete(), etc.
    // You can add custom query methods here if needed for specific queries.
}
