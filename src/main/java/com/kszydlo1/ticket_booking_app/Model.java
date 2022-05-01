package com.kszydlo1.ticket_booking_app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "models")
@Entity
public class Model {
    @Id
    @GeneratedValue
    private long id;

    private String content;

    public long getId() {
        return id;
    }

    public void setContent(String c){
        content = c;
    }

    public String getContent() {
        return content;
    }
}
