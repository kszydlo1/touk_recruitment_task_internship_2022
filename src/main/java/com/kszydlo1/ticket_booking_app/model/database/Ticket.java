package com.kszydlo1.ticket_booking_app.model.database;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "tickets")
@Entity
public class Ticket implements Serializable {
    @Id
    private String type;

    @NonNull
    private int price;

    @OneToMany(targetEntity = Seat.class)
    private List seats;

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public List getSeats() {
        return seats;
    }
}
