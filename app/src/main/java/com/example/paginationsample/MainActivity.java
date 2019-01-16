package com.example.paginationsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.paginationsample.response.FoodResponse;
import com.example.paginationsample.adapter.FoodAdapter;
import com.example.paginationsample.api.ApiClient;
import com.example.paginationsample.api.ApiInterface;
import com.example.paginationsample.model.FoodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FoodAdapter foodAdapter;
    private List<FoodModel> foodList = new ArrayList<>();
    private int pageNumber = 1;
    private static final int itemCount = 10;
    private boolean isInitialLoad = true;
    private boolean detectScrolling = true;
    private LinearLayoutManager linearLayoutManager;
    private String isEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        getFoodList();
        scrollListener();
    }

    private void scrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItems = linearLayoutManager.getChildCount();
                int totalItems = linearLayoutManager.getItemCount();
                int scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (detectScrolling) {
                    if ((currentItems + scrollOutItems) == totalItems) {
                        if (isEnd.equalsIgnoreCase("N")) {
                            getFoodList();
                            detectScrolling = false;
                        }
                    }
                }
            }

        });
    }

    private void getFoodList() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<FoodResponse> call = apiInterface.getFoodList(pageNumber, itemCount);
        call.enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<FoodResponse> call, @NonNull Response<FoodResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResult() != null) {
                            if (response.body().getResult().equalsIgnoreCase("success")) {
                                if (response.body().getFoodList() != null) {
                                    if (response.body().getIsEnd() != null) {
                                        isEnd = response.body().getIsEnd();
                                        if (isInitialLoad) {
                                            foodList.addAll(response.body().getFoodList());
                                            setRecyclerView();
                                            isInitialLoad = false;
                                        } else {
                                            foodList.addAll(response.body().getFoodList());
                                            foodAdapter.notifyDataSetChanged();
                                        }
                                        foodAdapter.setIsEnd(isEnd);
                                        detectScrolling = true;
                                        pageNumber++;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodResponse> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void setRecyclerView() {
        if (foodList != null && foodList.size() > 0) {
            recyclerView.setHasFixedSize(true);
            linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            foodAdapter = new FoodAdapter(MainActivity.this, foodList);
            recyclerView.setAdapter(foodAdapter);
        }
    }
}
