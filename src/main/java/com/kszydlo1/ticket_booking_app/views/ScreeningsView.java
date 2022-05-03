package com.kszydlo1.ticket_booking_app.views;

import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.requests.ScreeningsPeriodRequest;
import com.kszydlo1.ticket_booking_app.model.responses.ScreeningsPeriodResponse;
import com.kszydlo1.ticket_booking_app.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@RestController
public class ScreeningsView {
    @Autowired
    private ScreeningRepository screeningRepository;

    @GetMapping("/screenings")
    public Vector showScreenings(@RequestBody ScreeningsPeriodRequest sp){
        Vector response = new Vector<ScreeningsPeriodResponse>();
        List<Screening> screenings = (List<Screening>) screeningRepository.findAll()
                .stream()
                .filter(screening -> screening.getStartTime().after(sp.getStartDate())
                        && screening.getStartTime().before(sp.getEndDate()))
                .collect(Collectors.toList());
        for (Screening screening : screenings) {
            ScreeningsPeriodResponse screeningsPeriodResponse = new ScreeningsPeriodResponse();
            screeningsPeriodResponse.setScreeningId(screening.getScreeningId());
            screeningsPeriodResponse.setTitle(screening.getMovie().getTitle());
            screeningsPeriodResponse.setStartTime(screening.getStartTime());

            response.add(screeningsPeriodResponse);
        }
        return response;
    }
}
