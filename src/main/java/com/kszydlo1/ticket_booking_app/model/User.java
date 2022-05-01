package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;

    @OneToMany (targetEntity = Reservation.class)
    private List reservations;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
