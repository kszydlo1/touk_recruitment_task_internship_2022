package com.kszydlo1.ticket_booking_app;

//import com.kszydlo1.ticket_booking_app.repository.ScreeningRepository;
import com.kszydlo1.ticket_booking_app.model.database.*;
import com.kszydlo1.ticket_booking_app.model.requests.BookingRequest;
import com.kszydlo1.ticket_booking_app.model.requests.SeatSelectionRequest;
import com.kszydlo1.ticket_booking_app.model.responses.BookingResponse;
import com.kszydlo1.ticket_booking_app.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Controller {
    //@Autowired
    //private ScreeningRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningRoomRepository screeningRoomRepository;

    @Autowired
    private SeatSelectionRepository seatSelectionRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConstantRepository constantRepository;

    @GetMapping("/example_controller")
    public String example_controller() {
        //User model = new User();
        //model.setContent("Pierwszy");
        //repository.save(model);
        return "OK";
    }

    @GetMapping("/booking/{screeningId}")
    public BookingResponse bookSeats(@PathVariable long screeningId, @RequestBody BookingRequest bookingRequest) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getFirstName() == bookingRequest.getFirstName() &&
                        user.getLastName() == bookingRequest.getLastName())
                .collect(Collectors.toList());
        if(users.size() == 0) {
            User newUser = new User(bookingRequest.getFirstName(), bookingRequest.getLastName());
            userRepository.save(newUser);
        }
        users = userRepository.findAll()
                .stream()
                .filter(user -> user.getFirstName() == bookingRequest.getFirstName() &&
                        user.getLastName() == bookingRequest.getLastName())
                .collect(Collectors.toList());

        Screening screening = screeningRepository.findById(screeningId);
        Reservation reservation = new Reservation(screening, users.get(0), Calendar.getInstance());
        reservation = reservationRepository.save(reservation);

        ScreeningRoom screeningRoom = screeningRoomRepository.findById(screening.getScreeningRoom().getId());
        for (SeatSelectionRequest seatSelectionRequest : bookingRequest.getSeatSelectionRequests()) {
            int line = seatSelectionRequest.getLine();
            int column = seatSelectionRequest.getColumn();
            String ticket = seatSelectionRequest.getTicket();
            Seat seat = new Seat(line, column, screeningRoom);
            SeatSelection seatSelection = new SeatSelection(seat, reservation, screening);
            seatSelectionRepository.save(seatSelection);
        }
        List<Ticket> tickets = ticketRepository.findAll();
        int price = 0;
        for(SeatSelectionRequest seatSelectionRequest : bookingRequest.getSeatSelectionRequests()) {
            for (Ticket ticket : tickets) {
                if (seatSelectionRequest.getTicket().equals(ticket.getType())) {
                    price += ticket.getPrice();
                }
            }
        }
        Optional<Constant> expirationTimeMinutes = constantRepository.findById("expirationTimeMinutes");
        Calendar expirationTime = reservation.getDate();
        expirationTime.add(Calendar.MINUTE, expirationTimeMinutes.get().getConstValue());
        BookingResponse response = new BookingResponse(price, expirationTime);
        return response;
    }
}
