package com.kszydlo1.ticket_booking_app.model.requests;

import java.util.List;

public class BookingRequest {
    private List <SeatSelectionRequest> seatSelectionRequests;
    private String firstName;
    private String lastName;

    public List<SeatSelectionRequest> getSeatSelectionRequests() {
        return seatSelectionRequests;
    }

    public void setSeatSelectionRequests(List<SeatSelectionRequest> seatSelectionRequests) {
        this.seatSelectionRequests = seatSelectionRequests;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

