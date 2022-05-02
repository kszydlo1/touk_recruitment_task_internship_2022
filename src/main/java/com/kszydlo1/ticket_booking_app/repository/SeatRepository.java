package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.database.Seat;
import com.kszydlo1.ticket_booking_app.model.database.SeatPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat, SeatPK> {
    List<Seat> findAll();
}
