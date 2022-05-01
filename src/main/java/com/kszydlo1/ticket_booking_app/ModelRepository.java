package com.kszydlo1.ticket_booking_app;

import java.util.List;

import com.kszydlo1.ticket_booking_app.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<User, Long> {


}
