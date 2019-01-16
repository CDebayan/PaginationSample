package com.example.paginationsample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String base_url = "http://tutorialdb1.000webhostapp.com/tutorialAPIs/scripts/";
    private static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().
                    baseUrl(base_url).
                    addConverterFactory(
                            GsonConverterFactory.create()
                    ).build();
        }
        return retrofit;
    }
}
