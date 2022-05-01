package com.kszydlo1.ticket_booking_app;

import com.kszydlo1.ticket_booking_app.model.Movie;
import com.kszydlo1.ticket_booking_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
public class View {
    @Autowired
    private ModelRepository repository;

    @GetMapping("/example_view")
    public String example_view() {
        List <Movie> movies = repository.findAll();

        return movies.get(0).getTitle();
    }
}
