package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "reservations")
@Entity
public class Reservation implements Serializable {
    private Date date;

    @Id
    @ManyToOne
    private Screening screening;

    @Id
    @ManyToOne
    private User user;

    public Reservation(Screening screening, User user, Date date){
        this.screening = screening;
        this.user = user;
        this.date = date;
    }
}
