package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.ScreeningRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScreeningRoomRepository extends CrudRepository<ScreeningRoom, Long> {
    Optional<ScreeningRoom> findById(long screeningRoomId);
}
