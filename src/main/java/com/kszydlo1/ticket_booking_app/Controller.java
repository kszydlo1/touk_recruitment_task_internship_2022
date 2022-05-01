package com.kszydlo1.ticket_booking_app;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class Controller {
    @Autowired
    private ModelRepository repository;

    @GetMapping("/example_controller")
    public String example_controller() {
        Model model = new Model();
        model.setContent("Pierwszy");
        repository.save(model);
        return "OK";
    }
}
