package com.example.praktikum_project.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemListResponse {
    // SerializedName digunakan untuk menyamakan nama variabel di API dengan yang dibuat
    @SerializedName("results")
    private ArrayList<Item> results;

    public ItemListResponse(ArrayList<Item> results) {
        this.results = results;
    }

    public ArrayList<Item> getResults() {
        return results;
    }

}
