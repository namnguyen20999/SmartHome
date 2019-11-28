package com.example.myapplication;

public class light {
    String Id;
    boolean light1;
    boolean light2;

    public light() {

    }

    public light(String Id, boolean light1, boolean light2){
        this.Id = Id;
        this.light1 = light1;
        this.light2 = light2;
    }

    public String getId(){
        return Id;
    }

    public boolean getLight1(){
        return light1;
    }

    public boolean getLight2(){
        return light2;
    }
}
