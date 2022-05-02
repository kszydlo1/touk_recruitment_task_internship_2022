package com.kszydlo1.ticket_booking_app.model.requests;

import java.util.Calendar;


public class ScreeningsPeriodRequest {

    private String yyyyMMDD;

    private String startHHMM;

    private String endHHMM;

    public int getYear() {
        return Integer.parseInt(yyyyMMDD.substring(0, 4));
    }

    public int getMonth() {
        return Integer.parseInt(yyyyMMDD.substring(4, 6)) - 1;
    }

    public int getDay() {
        return Integer.parseInt(yyyyMMDD.substring(6, 8));
    }

    public int getStartHour() {
        return Integer.parseInt(startHHMM.substring(0, 2));
    }

    public int getStartMinutes() {
        return Integer.parseInt(startHHMM.substring(2, 4));
    }

    public int getEndHour() {
        return Integer.parseInt(endHHMM.substring(0, 2));
    }

    public int getEndMinutes() {
        return Integer.parseInt(endHHMM.substring(2, 4));
    }

    public Calendar getStartDate() {
        Calendar calendar = Calendar.getInstance(/*TimeZone.getTimeZone(ZoneOffset.UTC)*/);
        calendar.set(this.getYear(), this.getMonth(), this.getDay(), this.getStartHour(), this.getStartMinutes(), 00);
        //calendar.setTimeZone(TimeZone.getTimeZone("GMT" + 2));
        return calendar;
    }

    public Calendar getEndDate() {
        Calendar calendar = Calendar.getInstance(/*TimeZone.getTimeZone(ZoneOffset.UTC)*/);
        calendar.set(this.getYear(), this.getMonth(), this.getDay(), this.getEndHour(), this.getEndMinutes(), 00);
        //calendar.setTimeZone(TimeZone.getTimeZone("GMT" + 2));
        return calendar;
    }

    public void setYyyyMMDD(String yyyyMMDD) {
        this.yyyyMMDD = yyyyMMDD;
    }

    public void setStartHHMM(String startHHMM) {
        this.startHHMM = startHHMM;
    }

    public void setEndHHMM(String endHHMM) {
        this.endHHMM = endHHMM;
    }

}
