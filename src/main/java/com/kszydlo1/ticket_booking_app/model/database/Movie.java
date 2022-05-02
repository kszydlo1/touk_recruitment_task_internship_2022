package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "movies")
@Entity
public class Movie implements Serializable {
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

    public long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public List getScreenings() {
        return screenings;
    }
}
