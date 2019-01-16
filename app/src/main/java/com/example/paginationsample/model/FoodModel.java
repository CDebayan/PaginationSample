package com.example.paginationsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodModel {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
