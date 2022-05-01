package com.kszydlo1.ticket_booking_app;

import java.util.List;

import com.kszydlo1.ticket_booking_app.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Movie, Long> {
    List<Movie> findAll();
}
