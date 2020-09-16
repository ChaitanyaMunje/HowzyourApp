package com.gtappdevelopers.howzyourapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonFeed {

    public JsonFeed(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }

    @SerializedName("items")
    @Expose
    private ArrayList<Items> itemsArrayList;


    @Override
    public String toString() {
        return "JsonFeed{" +
                "itemsArrayList=" + itemsArrayList +
                '}';
    }

    public ArrayList<Items> getItemsArrayList() {
        return itemsArrayList;
    }

    public void setItemsArrayList(ArrayList<Items> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }
}
