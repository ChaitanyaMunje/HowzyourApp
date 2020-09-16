package com.gtappdevelopers.howzyourapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    public Items(String full_name, Owner owner, String description) {
        this.full_name = full_name;
        this.owner = owner;
        this.description = description;
    }

    @SerializedName("full_name")
    @Expose
    private String full_name;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("description")
    @Expose
    private String description;

    @Override
    public String toString() {
        return "Items{" +
                "full_name='" + full_name + '\'' +
                ", owner=" + owner +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
