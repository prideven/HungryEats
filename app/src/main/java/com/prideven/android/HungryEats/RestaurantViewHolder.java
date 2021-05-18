package com.prideven.android.hungryeats;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding;

public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    private RestaurantLayoutBinding restaurantLayoutBinding;
    private int resstaurant_id;
    public RestaurantViewHolder(RestaurantLayoutBinding restaurantLayoutBinding) {
        super(restaurantLayoutBinding.getRoot());
        this.restaurantLayoutBinding = restaurantLayoutBinding;
    }
    public void setData(EatsRestaurantsResponseItem eatsRestaurantsResponseItem,final GetRestaurantIDListener listener) {
        restaurantLayoutBinding.restaurantName.setText(eatsRestaurantsResponseItem.getName());
        restaurantLayoutBinding.deliveryFee.setText("Delivery Fee: " +eatsRestaurantsResponseItem.getDelivery_fee() + "cents");
        restaurantLayoutBinding.status.setText("Delivery Time: " + eatsRestaurantsResponseItem.getStatus());
        Glide.with(restaurantLayoutBinding.getRoot().getContext())
                .load(eatsRestaurantsResponseItem.getCover_img_url())
                .centerCrop()
                .into((restaurantLayoutBinding.image));
        resstaurant_id=eatsRestaurantsResponseItem.getId();
        restaurantLayoutBinding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(eatsRestaurantsResponseItem.getId());
            }
        });
    }
}