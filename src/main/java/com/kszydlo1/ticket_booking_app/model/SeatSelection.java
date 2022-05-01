package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    public SeatSelection() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatSelection that = (SeatSelection) o;
        return Objects.equals(seat, that.seat) && Objects.equals(reservation, that.reservation) && Objects.equals(screening, that.screening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seat, reservation, screening);
    }
}
