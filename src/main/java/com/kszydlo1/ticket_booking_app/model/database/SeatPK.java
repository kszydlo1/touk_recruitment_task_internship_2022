package com.kszydlo1.ticket_booking_app.model.database;

import java.io.Serializable;

public class SeatPK implements Serializable {
    private int line;
    private int column;
    private long screeningRoom;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public long getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(long screeningRoom) {
        this.screeningRoom = screeningRoom;
    }

    
}
