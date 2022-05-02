package com.kszydlo1.ticket_booking_app.model.database;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ScreeningPK implements Serializable {
    private Calendar startTime;
    private Movie movie;
    private ScreeningRoom screeningRoom;
}
