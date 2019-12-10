package com.example.myapplication;

public class tempTime {

    String temperature;
    String date;
    public tempTime() {

    }

    public tempTime(String temperature, String date) {
        this.temperature = temperature;
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }
    public String getDate(){
        return date;
    }
}
