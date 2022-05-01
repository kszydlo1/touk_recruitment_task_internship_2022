package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "seat_selections")
@Entity
public class SeatSelection implements Serializable {
    @Id
    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Reservation reservation;

    @Id
    @ManyToOne
    private Screening screening;

    public SeatSelection(Seat seat, Reservation reservation, Screening screening){
        this.seat = seat;
        this.reservation = reservation;
        this.screening = screening;
    }
}
