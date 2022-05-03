package com.kszydlo1.ticket_booking_app.model.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "constants")
@Entity
public class Constant {
    @Id
    private String name;
    private int constValue;

    public String getName() {
        return name;
    }

    public int getConstValue() {
        return constValue;
    }

    public Constant() {
    }
}
