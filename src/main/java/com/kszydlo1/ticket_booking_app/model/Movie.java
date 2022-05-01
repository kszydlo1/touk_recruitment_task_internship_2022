package com.kszydlo1.ticket_booking_app.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String title;

    @OneToMany (targetEntity = Screening.class)
    private List screenings;

    public Movie(String title){
        this.title = title;
    }

    public Movie() {};

    public String getTitle(){
        return title;
    }
}
