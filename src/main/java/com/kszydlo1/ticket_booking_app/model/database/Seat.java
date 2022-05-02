package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "seats")
@Entity
@IdClass(SeatPK.class)
public class Seat implements Serializable {
    @Id
    private int line;       // 'row' word is reserved in SQL

    @Id
    private int column;

    @Id
    @ManyToOne
    private ScreeningRoom screeningRoom;


    public Seat(int row, int column, ScreeningRoom screeningRoom){
        this.line = row;
        this.column = column;
        this.screeningRoom = screeningRoom;
    }

    public Seat() {};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return line == seat.line && column == seat.column && Objects.equals(screeningRoom, seat.screeningRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column, screeningRoom);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public ScreeningRoom getScreeningRoom() {
        return screeningRoom;
    }
}
