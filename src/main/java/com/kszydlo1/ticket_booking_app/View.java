package com.kszydlo1.ticket_booking_app;

import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.requests.ScreeningsPeriod;

import com.kszydlo1.ticket_booking_app.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class View {
    @Autowired
    private ScreeningRepository repository;

    //@GetMapping("/example_view")
    //public String example_view(@RequestParam String atrybut) {
        //System.out.println(atrybut);
        //List <Movie> movies = repository.findAll();

        //return movies.get(0).getTitle();
    //}

    @GetMapping("/screenings")
    public Map showScreenings(@RequestBody ScreeningsPeriod sp){
        Map response = new HashMap<String, Calendar>();
        List<Screening> screenings = (List<Screening>) repository.findAll()
                .stream()
                .filter(screening -> screening.getStartTime().after(sp.getStartDate())
                        && screening.getStartTime().before(sp.getEndDate()))
                .collect(Collectors.toList());
        for (Screening screening : screenings) {
            response.put(screening.getMovie().getTitle(), screening.getStartTime());
        }
        return response;
    }
}
