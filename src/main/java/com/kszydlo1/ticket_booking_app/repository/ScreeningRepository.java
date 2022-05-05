package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.Screening;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScreeningRepository extends CrudRepository<Screening, Long> {
    List<Screening> findAll();
    Optional<Screening> findById(long screeningId);
}
