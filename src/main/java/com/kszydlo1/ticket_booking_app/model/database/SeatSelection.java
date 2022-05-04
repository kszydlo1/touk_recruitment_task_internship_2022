package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "seat_selections")
@Entity
@IdClass(SeatSelectionPK.class)
public class SeatSelection implements Serializable {
    @Id
    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Reservation reservation;

    @Id
    @ManyToOne
    private Screening screening;

    @ManyToOne
    private Ticket ticket = null;

    public SeatSelection(Seat seat, Reservation reservation, Screening screening, Ticket ticket){
        this.seat = seat;
        this.reservation = reservation;
        this.screening = screening;
        this.ticket = ticket;
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

    public Screening getScreening() {
        return screening;
    }

    public Seat getSeat() {
        return seat;
    }
}
