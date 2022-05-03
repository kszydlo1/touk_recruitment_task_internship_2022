package com.kszydlo1.ticket_booking_app.model.responses;

import java.util.Calendar;

public class BookingResponse {
    private int totalAmountToPay;
    private Calendar reservationExpirationTime;

    public BookingResponse(int totalAmountToPay, Calendar reservationExpirationTime) {
        this.totalAmountToPay = totalAmountToPay;
        this.reservationExpirationTime = reservationExpirationTime;
    }

    public void setTotalAmountToPay(int totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    public void setReservationExpirationTime(Calendar reservationExpirationTime) {
        this.reservationExpirationTime = reservationExpirationTime;
    }

    public int getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public Calendar getReservationExpirationTime() {
        return reservationExpirationTime;
    }
}
