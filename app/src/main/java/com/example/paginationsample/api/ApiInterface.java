package com.example.paginationsample.api;


import com.example.paginationsample.response.FoodResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getFoodList.php")
    Call<FoodResponse> getFoodList(@Query("pageNumber") int pageNumber, @Query("itemCount") int itemCount);
}
