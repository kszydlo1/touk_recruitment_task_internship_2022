package com.kszydlo1.ticket_booking_app.controllers;

import com.kszydlo1.ticket_booking_app.Constants;
import com.kszydlo1.ticket_booking_app.model.database.*;
import com.kszydlo1.ticket_booking_app.model.requests.BookingRequest;
import com.kszydlo1.ticket_booking_app.model.requests.SeatSelectionRequest;
import com.kszydlo1.ticket_booking_app.model.responses.BookingResponse;
import com.kszydlo1.ticket_booking_app.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private SeatRepository seatRepository;

    private class NoBookingTimeException extends Exception {};
    private class NameNotAcceptableException extends Exception {};
    private class NoSeatException extends  Exception {};
    private class BookingTakenSeatsException extends Exception {};
    private class EmptySeatBetweenTakenSeatsException extends Exception {};

    @GetMapping("/booking/{screeningId}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
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
        } catch (BookingTakenSeatsException e) {
            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_EXCEPTION,
                    Constants.Controllers.BOOKING_TAKEN_SEAT_EXCEPTION);
        } catch (EmptySeatBetweenTakenSeatsException e) {
            return new BookingResponse(Constants.Controllers.BOOKING_STATUS_EXCEPTION,
                    Constants.Controllers.EMPTY_SEAT_BETWEEN_TAKEN_SEATS_EXCEPTION);
        }

    }

    private void checkConstraints(Screening screening, BookingRequest bookingRequest) throws NoBookingTimeException,
            NameNotAcceptableException, NoSeatException, BookingTakenSeatsException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -Constants.Controllers.NO_BOOKING_TIME_MINUTES_CONST);
        if (screening.getStartTime().after(calendar))
            throw new NoBookingTimeException();
        if (bookingRequest.getFirstName().length() < Constants.Controllers.NAME_MIN_LENGTH)
            throw new NameNotAcceptableException();
        if (!Character.isUpperCase(bookingRequest.getFirstName().charAt(0)))
            throw new NameNotAcceptableException();
               
        String[] names = bookingRequest.getLastName().split("-");
        if (names.length > 2)
            throw new NameNotAcceptableException();
        for (String name : names) {
            if (name.length() < Constants.Controllers.NAME_MIN_LENGTH)
                throw new NameNotAcceptableException();
            if (!Character.isUpperCase(name.charAt(0)))
                throw new NameNotAcceptableException();
        }
        
        if (bookingRequest.getSeatSelectionRequests().size() < 1)
            throw new NoSeatException();
        if (bookingTakenSeats(screening, bookingRequest.getSeatSelectionRequests())){
            throw new BookingTakenSeatsException();
        }
    }

    private boolean bookingTakenSeats(Screening screening, List <SeatSelectionRequest> seatSelectionRequests){
        List <SeatSelection> takenSeats = getTakenSeats(screening);
        for (SeatSelectionRequest seatSelectionRequest : seatSelectionRequests){
            for (SeatSelection takenSeat : takenSeats){
                if (seatSelectionRequest.getColumn() == takenSeat.getSeat().getColumn() && seatSelectionRequest.getLine() == takenSeat.getSeat().getLine())
                    return true;
            }
        }
        return false;
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

    @Transactional(rollbackFor = Exception.class)
    private void saveSeatSelections(List <SeatSelectionRequest> req, ScreeningRoom sR, Reservation res, Screening sc) throws EmptySeatBetweenTakenSeatsException {
        for (SeatSelectionRequest seatSelectionRequest : req) {
            int line = seatSelectionRequest.getLine();
            int column = seatSelectionRequest.getColumn();
            String ticketName = seatSelectionRequest.getTicket();
            Ticket ticket = ticketRepository.findById(ticketName).get();
            Seat seat = new Seat(line, column, sR);
            SeatSelection seatSelection = new SeatSelection(seat, res, sc, ticket);
            seatSelectionRepository.save(seatSelection);
        }
        checkNoSinglePlaceConstraint(sc, sR);
    }
    private void checkNoSinglePlaceConstraint(Screening sc, ScreeningRoom sR) throws EmptySeatBetweenTakenSeatsException {
        List <SeatSelection> seatSelections = getSeatSelections(sc);
        List <Seat> seats = getSeats(sR);
        analyseSeatsSelections(seatSelections, seats);
    }

    private List <SeatSelection> getSeatSelections(Screening sc){
        List <SeatSelection> seatSelections = seatSelectionRepository.findAll()
            .stream()
            .filter(seatSelection -> seatSelection.getScreening() == sc)
            .collect(Collectors.toList());
        return seatSelections;
    }

    private List <Seat> getSeats(ScreeningRoom sR){
        List <Seat> seats = seatRepository.findAll()
            .stream()
            .filter(seat -> seat.getScreeningRoom() == sR)
            .collect(Collectors.toList());
        return seats;
    }

    private void analyseSeatsSelections(List <SeatSelection> seatSelections, List <Seat> seats) throws EmptySeatBetweenTakenSeatsException{
        for(SeatSelection seatSelection1 : seatSelections){
            boolean correct = false;
            for(Seat nextSeat : seats)
                if (seatSelection1.getSeat().getLine() == nextSeat.getLine() &&
                    seatSelection1.getSeat().getColumn() == nextSeat.getColumn() - 1)
                        for (SeatSelection seatSelection2 : seatSelections)
                            if (seatSelection2.getSeat() == nextSeat )
                                correct = true;
            
            if(seatSelection1.getSeat().getColumn() == getMaxColumn(seats))
                correct = true;
            if(!correct)
                throw new EmptySeatBetweenTakenSeatsException();
        }
    }

    private int getMaxColumn(List <Seat> seats) {
        int maxColumn = 0;
        for (Seat seat : seats)
            if (seat.getColumn() > maxColumn)
                maxColumn = seat.getColumn();
        return maxColumn;
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

    private List<SeatSelection> getTakenSeats(Screening screening) {
        List <SeatSelection> takenSeats = seatSelectionRepository.findAll()
                .stream()
                .filter(seatSelection -> seatSelection.getScreening() == screening)
                .collect(Collectors.toList());
        return takenSeats;
    }
}
