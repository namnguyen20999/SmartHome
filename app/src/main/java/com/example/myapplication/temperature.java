package com.example.myapplication;


public class temperature {

    String temperature;
    String tempId;
    String date;
    public temperature() {

    }

    public temperature(String temperature, String tempId, String date) {
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
    public String getDate(){
        return date;
    }
}