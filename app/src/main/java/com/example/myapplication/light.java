package com.example.myapplication;

public class light {
    String Id;
    String light1;
    String light2;

    public light(String light1State, String light2State) {

    }

    public light(String Id, String light1, String light2){
        this.Id = Id;
        this.light1 = light1;
        this.light2 = light2;
    }

    public String getId(){
        return Id;
    }

    public String getLight1(){
        return light1;
    }

    public String getLight2(){
        return light2;
    }
}
