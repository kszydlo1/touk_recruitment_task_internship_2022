package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "seats")
@Entity
public class Seat implements Serializable {
    @Id
    private int row;

    @Id
    private int column;

    @Id
    @ManyToOne
    private ScreeningRoom screeningRoom;

    public Seat(int row, int column, ScreeningRoom screeningRoom){
        this.row = row;
        this.column = column;
        this.screeningRoom = screeningRoom;
    }
}
