package com.kszydlo1.ticket_booking_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class View {
    @Autowired
    private ModelRepository repository;

    @GetMapping("/example_view")
    public List example_view() {
        List<Model> models = repository.findAll();
        return models;
    }
}
