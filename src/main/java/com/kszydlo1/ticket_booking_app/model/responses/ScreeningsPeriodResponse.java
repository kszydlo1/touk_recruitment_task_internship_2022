package com.kszydlo1.ticket_booking_app.model.responses;

import java.util.Calendar;

public class ScreeningsPeriodResponse {
    private String title;
    private Calendar startTime;
    private long screeningRoomId;

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

    public long getScreeningRoomId() {
        return screeningRoomId;
    }

    public void setScreeningRoomId(long screeningRoomId) {
        this.screeningRoomId = screeningRoomId;
    }
}
