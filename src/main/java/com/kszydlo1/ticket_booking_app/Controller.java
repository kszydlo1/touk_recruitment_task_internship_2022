package com.kszydlo1.ticket_booking_app;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/example_controller")
    public Model example_controller() {
        return new Model(counter.incrementAndGet(), "Hello world!");
    }
}
