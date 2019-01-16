package com.example.paginationsample.Response;

import com.example.paginationsample.model.FoodModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {
    @SerializedName("foodList")
    @Expose
    private List<FoodModel> foodList = null;
    @SerializedName("isEnd")
    @Expose
    private String isEnd;
    @SerializedName("result")
    @Expose
    private String result;

    public List<FoodModel> getFoodList() {
        return foodList;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public String getResult() {
        return result;
    }
}
