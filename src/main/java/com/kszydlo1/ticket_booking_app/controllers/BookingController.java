package com.kszydlo1.ticket_booking_app.controllers;

import com.kszydlo1.ticket_booking_app.Constants;
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
public class BookingController {
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

    @GetMapping("/booking/{screeningId}")
    public BookingResponse bookSeats(@PathVariable long screeningId, @RequestBody BookingRequest bookingRequest) {
        User user = getUser(bookingRequest);
        Screening screening = screeningRepository.findById(screeningId).get();
        Reservation reservation = saveReservation(screening, user);
        ScreeningRoom screeningRoom = screeningRoomRepository.findById(screening.getScreeningRoom().getId()).get();

        saveSeatSelections(bookingRequest.getSeatSelectionRequests(), screeningRoom, reservation, screening);

        int totalAmountToPay = getTotalAmountToPay(bookingRequest.getSeatSelectionRequests());
        Calendar expirationTime = getExpirationTime(reservation);

        return new BookingResponse(totalAmountToPay, expirationTime);
    }

    private User getUser(BookingRequest bookingRequest) {
        Optional <User> optionalUser = findUser(bookingRequest.getFirstName(), bookingRequest.getLastName());
        return optionalUser.isPresent() ?
                optionalUser.get() : saveUser(bookingRequest.getFirstName(), bookingRequest.getLastName());
    }

    private Optional <User> findUser(String firstName, String lastName) {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getFirstName() == firstName && user.getLastName() == lastName)
                .collect(Collectors.toList());
        return users.stream().findFirst();
    }

    private User saveUser(String firstName, String lastName) {
        User newUser = new User(firstName, lastName);
        return userRepository.save(newUser);
    }

    private Reservation saveReservation(Screening screening, User user) {
        Reservation reservation = new Reservation(screening, user, Calendar.getInstance());
        return reservationRepository.save(reservation);
    }

    private void saveSeatSelections(List <SeatSelectionRequest> req, ScreeningRoom sR, Reservation res, Screening sc) {
        for (SeatSelectionRequest seatSelectionRequest : req) {
            int line = seatSelectionRequest.getLine();
            int column = seatSelectionRequest.getColumn();
            String ticket = seatSelectionRequest.getTicket();
            Seat seat = new Seat(line, column, sR);
            SeatSelection seatSelection = new SeatSelection(seat, res, sc);
            seatSelectionRepository.save(seatSelection);
        }
    }

    private int getTotalAmountToPay(List <SeatSelectionRequest> req) {
        List<Ticket> tickets = ticketRepository.findAll();
        int price = 0;
        for(SeatSelectionRequest seatSelectionRequest : req)
            for (Ticket ticket : tickets)
                if (seatSelectionRequest.getTicket().equals(ticket.getType()))
                    price += ticket.getPrice();
        return price;
    }

    private Calendar getExpirationTime(Reservation reservation) {
        Optional<Constant> expirationTimeMinutes = constantRepository
                .findById(Constants.Controllers.EXPIRATION_TIME_CONST);
        Calendar expirationTime = reservation.getDate();
        expirationTime.add(Calendar.MINUTE, expirationTimeMinutes.get().getConstValue());
        return expirationTime;
    }
}
