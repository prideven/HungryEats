package com.prideven.android.hungryeats

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prideven.android.hungryeats.databinding.RestaurantLayoutBinding

class RestaurantViewHolder(private val restaurantLayoutBinding: RestaurantLayoutBinding) :
    RecyclerView.ViewHolder(restaurantLayoutBinding.root) {
    private var resstaurant_id = 0
    fun setData(
        eatsRestaurantsResponseItem: EatsRestaurantsResponseItem,
        listener: GetRestaurantIDListener
    ) {
        restaurantLayoutBinding.restaurantName.text = eatsRestaurantsResponseItem.name
        restaurantLayoutBinding.deliveryFee.text =
            "Delivery Fee: " + eatsRestaurantsResponseItem.delivery_fee + "cents"
        restaurantLayoutBinding.status.text = "Delivery Time: " + eatsRestaurantsResponseItem.status
        Glide.with(restaurantLayoutBinding.root.context)
            .load(eatsRestaurantsResponseItem.cover_img_url)
            .centerCrop()
            .into(restaurantLayoutBinding.image)
        resstaurant_id = eatsRestaurantsResponseItem.id
        restaurantLayoutBinding.image.setOnClickListener {
            listener.onItemClick(
                eatsRestaurantsResponseItem.id
            )
        }
    }

}