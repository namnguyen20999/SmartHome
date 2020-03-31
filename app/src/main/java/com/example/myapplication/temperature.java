package com.example.myapplication;


public class temperature {

    String temperature;
    String date;
    public temperature() {

    }

    public temperature(String temperature, String date) {
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