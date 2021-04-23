package com.prideven.android.hungryeats;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder>  {
    private static final String TAG = "RestuarantAdapter";

    private RestaurantLayoutBinding rbinding;
    private GetRestaurantIDListener listener;

    List<EatsRestaurantsResponseItem> items = new ArrayList<>();

    public RestaurantAdapter(List<EatsRestaurantsResponseItem> dataSet, GetRestaurantIDListener listener) {
        this.items = dataSet;
        this.listener = listener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        rbinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.restaurant_layout,
                        parent,
                        false);

        return new RestaurantViewHolder(rbinding);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.setData(items.get(position),listener);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public void setData(List<EatsRestaurantsResponseItem> dataSet) {
        this.items = dataSet;
        notifyDataSetChanged();
    }
}