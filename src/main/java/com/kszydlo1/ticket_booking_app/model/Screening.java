package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    public Screening() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(startTime, screening.startTime) && Objects.equals(movie, screening.movie) && Objects.equals(screeningRoom, screening.screeningRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, movie, screeningRoom);
    }
}
