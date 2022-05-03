package com.kszydlo1.ticket_booking_app.controllers;

import com.kszydlo1.ticket_booking_app.Constants;
import com.kszydlo1.ticket_booking_app.model.database.*;
import com.kszydlo1.ticket_booking_app.model.requests.BookingRequest;
import com.kszydlo1.ticket_booking_app.model.requests.SeatSelectionRequest;
import com.kszydlo1.ticket_booking_app.model.responses.BookingResponse;
import com.kszydlo1.ticket_booking_app.repository.*;
import org.hibernate.FetchNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
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

    private class NoBookingTimeException extends Exception {};
    private class NameNotAcceptableException extends Exception {};
    private class NoSeatException extends  Exception {};

    @GetMapping("/booking/{screeningId}")
    public BookingResponse bookSeats(@PathVariable long screeningId, @RequestBody BookingRequest bookingRequest) {
        try {
            User user = getUser(bookingRequest);
            Screening screening = screeningRepository.findById(screeningId).get();

            checkConstraints(screening, bookingRequest);

            Reservation reservation = saveReservation(screening, user);
            ScreeningRoom screeningRoom = screeningRoomRepository.findById(screening.getScreeningRoom().getId()).get();

            saveSeatSelections(bookingRequest.getSeatSelectionRequests(), screeningRoom, reservation, screening);

            int totalAmountToPay = getTotalAmountToPay(bookingRequest.getSeatSelectionRequests());
            Calendar expirationTime = getExpirationTime(reservation);

            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_OK, totalAmountToPay, expirationTime);

        }
        catch (NoSuchElementException e) {
            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_EXCEPTION,
                    Constants.Controllers.NO_SUCH_SCREENING_EXCEPTION);
        }
        catch (NoBookingTimeException e) {
            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_EXCEPTION,
                    Constants.Controllers.NO_BOOKING_TIME_EXCEPTION);
        }
        catch (NameNotAcceptableException e) {
            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_EXCEPTION,
                    Constants.Controllers.NAME_NOT_ACCEPTABLE_EXCEPTION);
        }
        catch (NoSeatException e) {
            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_EXCEPTION,
                    Constants.Controllers.NO_SEAT_EXCEPTION);
        }

    }

    private void checkConstraints(Screening screening, BookingRequest bookingRequest) throws NoBookingTimeException,
            NameNotAcceptableException, NoSeatException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -Constants.Controllers.NO_BOOKING_TIME_MINUTES_CONST);
        if (screening.getStartTime().after(calendar))
            throw new NoBookingTimeException();
        if (bookingRequest.getFirstName().length() < Constants.Controllers.NAME_MIN_LENGTH)
            throw new NameNotAcceptableException();
        if (bookingRequest.getLastName().length() < Constants.Controllers.NAME_MIN_LENGTH)
            throw new NameNotAcceptableException();
        if (bookingRequest.getLastName().contains("-")) {
            String[] names = bookingRequest.getLastName().split("-");
            for (String name : names)
                if (name.length() < Constants.Controllers.NAME_MIN_LENGTH)
                    throw new NameNotAcceptableException();
        }
        if (bookingRequest.getSeatSelectionRequests().size() < 1)
            throw new NoSeatException();
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

    @Transactional
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
