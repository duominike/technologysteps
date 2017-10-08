package com.joker.tecsteps.javatecsteps.functional.model;

/**
 * Created by joker on 17-9-24.
 */
public class Track {
    public String name;
    public int length;
    public Track(String name, int length){
        this.name = name;
        this.length = length;
    }
    public int getLength(){
        return this.length;
    }
}
