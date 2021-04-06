package com.prideven.android.hungryeats;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding;


public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    private RestaurantLayoutBinding restaurantLayoutBinding;

    public RestaurantViewHolder(RestaurantLayoutBinding restaurantLayoutBinding) {
        super(restaurantLayoutBinding.getRoot());
        this.restaurantLayoutBinding = restaurantLayoutBinding;
    }

    public void setData(EatsRestaurantsResponseItem eatsRestaurantsResponseItem) {

        restaurantLayoutBinding.restaurantName.setText(eatsRestaurantsResponseItem.getName());
        restaurantLayoutBinding.deliveryFee.setText("Delivery Fee: " +eatsRestaurantsResponseItem.getDelivery_fee() + "cents");
        restaurantLayoutBinding.status.setText("Delivery Time: " + eatsRestaurantsResponseItem.getStatus());
        Glide.with(restaurantLayoutBinding.getRoot().getContext())
                .load(eatsRestaurantsResponseItem.getCover_img_url())
                .centerCrop()
                .into((restaurantLayoutBinding.image));


    }
}