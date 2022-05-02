package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.database.ScreeningPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, ScreeningPK> {
    List<Screening> findAll();
}
