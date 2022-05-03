package com.kszydlo1.ticket_booking_app.views;

import com.kszydlo1.ticket_booking_app.Constants;
import com.kszydlo1.ticket_booking_app.model.database.Screening;
import com.kszydlo1.ticket_booking_app.model.database.Seat;
import com.kszydlo1.ticket_booking_app.model.database.SeatSelection;
import com.kszydlo1.ticket_booking_app.model.responses.SeatsResponse;
import com.kszydlo1.ticket_booking_app.repository.ScreeningRepository;
import com.kszydlo1.ticket_booking_app.repository.SeatRepository;
import com.kszydlo1.ticket_booking_app.repository.SeatSelectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@RestController
public class SeatsView {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatSelectionRepository seatSelectionRepository;

    @GetMapping("/screening/{screeningId}")
    public Vector showSeats(@PathVariable long screeningId) {
        Screening screening = screeningRepository.findById(screeningId).get();
        List<Seat> allSeats = getAllSeats(screening);
        List<SeatSelection> takenSeats = getTakenSeats(screening);
        Vector response = createResponse(screening, allSeats, takenSeats);

        return response;
    }

    private List<Seat> getAllSeats(Screening screening) {
        List<Seat> allSeats = seatRepository.findAll()
                .stream()
                .filter(seat -> seat.getScreeningRoom() == screening.getScreeningRoom())
                .collect(Collectors.toList());
        return allSeats;
    }

    private List<SeatSelection> getTakenSeats(Screening screening) {
        List <SeatSelection> takenSeats = (List<SeatSelection>) seatSelectionRepository.findAll()
                .stream()
                .filter(seatSelection -> seatSelection.getScreening() == screening)
                .collect(Collectors.toList());
        return takenSeats;
    }

    private Vector createResponse(Screening screening, List<Seat> allSeats, List <SeatSelection> takenSeats) {
        Vector <SeatsResponse> response = new Vector<>();
        for(Seat seat : allSeats) {
            SeatsResponse seatsResponse = new SeatsResponse();
            seatsResponse.setLine(seat.getLine());
            seatsResponse.setColumn(seat.getColumn());
            takenSeats.stream().filter(takenSeat -> takenSeat.getSeat() == seat).collect(Collectors.toList());
            if(takenSeats.isEmpty())
                seatsResponse.setStatus(Constants.Views.FREE_SEAT_CONST);
            else
                seatsResponse.setStatus(Constants.Views.TAKEN_SEAT_CONST);
            response.add(seatsResponse);
        }
        return response;
    }
}
