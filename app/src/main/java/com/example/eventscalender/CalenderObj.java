package com.example.eventscalender;

public class CalenderObj {
    int day;
    String year,month,eventType;

    public CalenderObj(int day, String year, String month, String eventType) {
        this.day = day;
        this.year = year;
        this.month = month;
        this.eventType = eventType;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
