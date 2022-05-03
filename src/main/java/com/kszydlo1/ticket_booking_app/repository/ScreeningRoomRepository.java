package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.ScreeningRoom;
import org.springframework.data.repository.CrudRepository;

public interface ScreeningRoomRepository extends CrudRepository<ScreeningRoom, Long> {
    ScreeningRoom findById(long screeningRoomId);
}
