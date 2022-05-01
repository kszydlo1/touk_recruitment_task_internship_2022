package com.kszydlo1.ticket_booking_app;

import com.kszydlo1.ticket_booking_app.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class Controller {
    @Autowired
    private ModelRepository repository;

    @GetMapping("/example_controller")
    public String example_controller() {
        //User model = new User();
        //model.setContent("Pierwszy");
        //repository.save(model);
        return "OK";
    }
}
