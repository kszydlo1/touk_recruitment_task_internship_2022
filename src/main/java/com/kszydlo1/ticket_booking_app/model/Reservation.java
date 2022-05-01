package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name = "reservations")
@Entity
public class Reservation implements Serializable {
    private Date date;

    @Id
    @ManyToOne
    private Screening screening;

    @Id
    @ManyToOne
    private User user;

    public Reservation(Screening screening, User user, Date date){
        this.screening = screening;
        this.user = user;
        this.date = date;
    }

    public Reservation() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(date, that.date) && Objects.equals(screening, that.screening) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, screening, user);
    }
}
