package com.joker.tecsteps.javatecsteps.functional.model;

import java.util.List;

/**
 * Created by joker on 17-9-24.
 */
public class Artist {
    public String name;
    public List<String> members;
    public String origin;
    public Artist(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isFrom(String country){
        return origin.startsWith(country);
    }
}
