package com.prideven.android.hungryeats

import com.google.gson.annotations.SerializedName


data class RestaurantsResponse(
    @SerializedName("data")
    val restaurantList: List<RestaurantDetails>,
    val more_pages: Boolean,
    val numResults: Int,
    val page: Int,
    val totalResults: Int,
    val total_pages: Int
)

data class RestaurantDetails(
    val address: Address,
    val cuisines: List<String>,
    val geo: Geo,
    val hours: String,
    val last_updated: String,
    val menus: List<Any>,
    val price_range: String,
    val price_range_num: Int,
    val restaurant_id: Long,
    val restaurant_name: String,
    val restaurant_phone: String,
    val restaurant_website: String
)

data class Address(
    val city: String,
    val formatted: String,
    val postal_code: String,
    val state: String,
    val street: String
)

data class Geo(
    val lat: Double,
    val lon: Double
)