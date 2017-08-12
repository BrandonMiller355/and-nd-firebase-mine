package com.google.firebase.udacity.friendlychat.models;

public class Restaurant {

    private String name;
    //TODO: Brandon - add stuff like who added it, what date it was added, address, vote counts

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
