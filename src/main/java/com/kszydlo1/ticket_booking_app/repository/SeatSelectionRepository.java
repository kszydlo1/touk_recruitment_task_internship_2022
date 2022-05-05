package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.SeatSelection;
import com.kszydlo1.ticket_booking_app.model.database.SeatSelectionPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatSelectionRepository extends CrudRepository<SeatSelection, SeatSelectionPK> {
    List<SeatSelection> findAll();
    SeatSelection save(SeatSelection seatSelection);
    void delete(SeatSelection seatSelection);
}
