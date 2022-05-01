package com.kszydlo1.ticket_booking_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class View {

    @GetMapping("/example_view")
    public String example_view() {
        return "example_view";
    }
}
