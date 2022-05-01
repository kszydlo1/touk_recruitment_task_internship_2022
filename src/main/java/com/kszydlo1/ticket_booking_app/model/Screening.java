package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "screenings")
@Entity
public class Screening implements Serializable {
    @Id
    private Date startTime;

    @Id
    @ManyToOne
    private Movie movie;

    @Id
    @ManyToOne
    private ScreeningRoom screeningRoom;

    public Screening(Movie movie, ScreeningRoom screeningRoom, Date startTime){
        this.movie = movie;
        this.screeningRoom = screeningRoom;
        this.startTime = startTime;
    }
}
