package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "screening_rooms")
@Entity
public class ScreeningRoom implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToMany (targetEntity = Screening.class)
    private List screenings;

    @OneToMany (targetEntity = Seat.class)
    private List seats;

    public ScreeningRoom() {};

    public long getId() {
        return id;
    }

    public List getScreenings() {
        return screenings;
    }

    public List getSeats() {
        return seats;
    }
}
