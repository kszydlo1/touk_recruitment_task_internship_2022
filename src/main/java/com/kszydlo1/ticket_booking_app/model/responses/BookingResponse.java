package com.kszydlo1.ticket_booking_app.model.responses;

import java.util.Calendar;

public class BookingResponse {
    private String status;
    private String message;
    private int totalAmountToPay;
    private Calendar reservationExpirationTime;

    public BookingResponse(String status, int totalAmountToPay, Calendar reservationExpirationTime) {
        this.status = status;
        this.totalAmountToPay = totalAmountToPay;
        this.reservationExpirationTime = reservationExpirationTime;
    }
    public BookingResponse(String status, String message) {
        this.status = status;
        this.message = message;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
