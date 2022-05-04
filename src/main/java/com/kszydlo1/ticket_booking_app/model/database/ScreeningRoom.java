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
    private List <Screening> screenings;

    @OneToMany (targetEntity = Seat.class)
    private List <Seat> seats;

    public ScreeningRoom() {};

    public long getId() {
        return id;
    }

    public List <Screening> getScreenings() {
        return screenings;
    }

    public List <Seat> getSeats() {
        return seats;
    }

    public int getMaxColumn() {
        int maxColumn = 0;
        for (Seat seat : this.seats)
            if (seat.getColumn() > maxColumn)
                maxColumn = seat.getColumn();
        return maxColumn;
    }
}
