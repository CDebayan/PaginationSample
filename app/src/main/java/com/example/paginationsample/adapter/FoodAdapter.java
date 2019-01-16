package com.example.paginationsample.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paginationsample.R;
import com.example.paginationsample.model.FoodModel;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private Activity activity;
    private List<FoodModel> foodList;
    private String isEnd;

    public FoodAdapter(Activity activity, List<FoodModel> foodList) {

        this.activity = activity;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.child_food_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setDataToFields(position);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;
        private final TextView description;
        private final ProgressBar progressBar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        private void setDataToFields(int position) {
            if (position == (foodList.size() - 1)) {
                if (isEnd.equalsIgnoreCase("N")) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            } else {
                progressBar.setVisibility(View.GONE);
            }
            Glide.with(activity).load(foodList.get(position).getImage()).into(image);
            name.setText(foodList.get(position).getName());
            description.setText(foodList.get(position).getDescription());
        }
    }
}
