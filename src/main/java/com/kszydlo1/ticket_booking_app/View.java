package com.kszydlo1.ticket_booking_app;

import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.database.Seat;
import com.kszydlo1.ticket_booking_app.model.database.SeatSelection;
import com.kszydlo1.ticket_booking_app.model.requests.ScreeningsPeriodRequest;

import com.kszydlo1.ticket_booking_app.model.responses.ScreeningsPeriodResponse;
import com.kszydlo1.ticket_booking_app.model.responses.SeatsResponse;
import com.kszydlo1.ticket_booking_app.repository.ScreeningRepository;
import com.kszydlo1.ticket_booking_app.repository.SeatRepository;
import com.kszydlo1.ticket_booking_app.repository.SeatSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class View {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatSelectionRepository seatSelectionRepository;

    //@GetMapping("/example_view")
    //public String example_view(@RequestParam String atrybut) {
        //System.out.println(atrybut);
        //List <Movie> movies = repository.findAll();

        //return movies.get(0).getTitle();
    //}

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

    @GetMapping("/screening/{screeningId}")
    public Vector showSeats(@PathVariable long screeningId) {
        Vector response = new Vector<>();
        Screening screening = screeningRepository.findById(screeningId);
        List <Seat> allSeats = (List<Seat>) seatRepository.findAll()
                .stream()
                .filter(seat -> seat.getScreeningRoom() == screening.getScreeningRoom())
                .collect(Collectors.toList());
        List <SeatSelection> takenSeats = (List<SeatSelection>) seatSelectionRepository.findAll()
                .stream()
                .filter(seatSelection -> seatSelection.getScreening() == screening)
                .collect(Collectors.toList());
        for(Seat seat : allSeats) {
            SeatsResponse seatsResponse = new SeatsResponse();
            seatsResponse.setLine(seat.getLine());
            seatsResponse.setColumn(seat.getColumn());
            takenSeats.stream().filter(takenSeat -> takenSeat.getSeat() == seat).collect(Collectors.toList());
            if(takenSeats.size() == 0) {
                seatsResponse.setStatus("free");
            }
            else {
                seatsResponse.setStatus("taken");
            }
            response.add(seatsResponse);
        }
        return response;
    }
}
