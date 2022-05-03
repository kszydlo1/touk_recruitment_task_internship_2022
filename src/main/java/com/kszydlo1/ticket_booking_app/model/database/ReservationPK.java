package com.kszydlo1.ticket_booking_app.model.database;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ReservationPK implements Serializable {
    private long screening;
    private long user;
}
