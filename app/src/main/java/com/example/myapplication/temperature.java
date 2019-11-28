package com.example.myapplication;


public class temperature {

    String temperature;
    String tempId;
    long date;
    public temperature() {

    }

    public temperature(String temperature, String tempId, long date) {
        this.temperature = temperature;
        this.tempId = tempId;
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }
    public String getTempId(){
        return tempId;
    }
    public long getDate(){
        return date;
    }
}