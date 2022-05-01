package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "screening_rooms")
@Entity
public class ScreeningRoom {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToMany (targetEntity = Screening.class)
    private List screenings;

    @OneToMany (targetEntity = Seat.class)
    private List seats;

    public ScreeningRoom() {};
}
