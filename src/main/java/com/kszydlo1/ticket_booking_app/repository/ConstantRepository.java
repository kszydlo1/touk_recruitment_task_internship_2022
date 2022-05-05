package com.kszydlo1.ticket_booking_app.repository;

import com.kszydlo1.ticket_booking_app.model.database.Constant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConstantRepository extends CrudRepository<Constant, String> {
    Optional<Constant> findById(String name);
}
