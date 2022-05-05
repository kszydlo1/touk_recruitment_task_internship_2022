package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.Reservation;
import com.kszydlo1.ticket_booking_app.model.database.ReservationPK;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, ReservationPK> {
    Reservation save(Reservation reservation);
}
