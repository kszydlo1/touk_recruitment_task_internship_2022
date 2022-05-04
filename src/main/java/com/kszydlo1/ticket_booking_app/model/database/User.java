package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue
    private long userId;

    private String firstName;
    private String lastName;

    @OneToMany (targetEntity = Reservation.class)
    private List <Reservation> reservations;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {};

    public long getId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List <Reservation> getReservations() {
        return reservations;
    }
}
