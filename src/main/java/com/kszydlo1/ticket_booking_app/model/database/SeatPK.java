package com.kszydlo1.ticket_booking_app.model.database;

import java.io.Serializable;

public class SeatPK implements Serializable {
    private int line;
    private int column;
    private ScreeningRoom screeningRoom;
}
