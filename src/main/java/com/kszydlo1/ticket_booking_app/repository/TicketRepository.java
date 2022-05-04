package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, String> {
    List<Ticket> findAll();
    Optional<Ticket> findById(String name);
}
