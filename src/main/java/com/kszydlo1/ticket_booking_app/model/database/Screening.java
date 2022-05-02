package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Table(name = "screenings")
@Entity
@IdClass(ScreeningPK.class)
public class Screening implements Serializable {
    @Id
    private Calendar startTime;

    @Id
    @ManyToOne
    private Movie movie;

    @Id
    @ManyToOne
    private ScreeningRoom screeningRoom;

    public Screening(Movie movie, ScreeningRoom screeningRoom, Calendar startTime){
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

    public Calendar getStartTime(){
        return startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }
}
