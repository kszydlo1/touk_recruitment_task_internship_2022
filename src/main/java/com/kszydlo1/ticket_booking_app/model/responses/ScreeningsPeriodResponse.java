package com.kszydlo1.ticket_booking_app.model.responses;

import java.util.Calendar;

public class ScreeningsPeriodResponse {
    private long screeningId;
    private String title;
    private Calendar startTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(long screeningId) {
        this.screeningId = screeningId;
    }
}
