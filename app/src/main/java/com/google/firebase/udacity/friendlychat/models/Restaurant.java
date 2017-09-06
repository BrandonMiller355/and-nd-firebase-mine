package com.google.firebase.udacity.friendlychat.models;

public class Restaurant {

    private String name;
    private String category;
    //TODO: Brandon - add stuff like who added it, what date it was added, address, vote counts


    public Restaurant() {
    }

    public Restaurant(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
