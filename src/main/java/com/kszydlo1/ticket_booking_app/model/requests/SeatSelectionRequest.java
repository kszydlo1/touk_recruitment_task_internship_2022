package com.kszydlo1.ticket_booking_app.model.requests;

public class SeatSelectionRequest {
    private int line;
    private int column;
    private String ticket;

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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}