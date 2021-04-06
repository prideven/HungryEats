package com.prideven.android.hungryeats

class EatsRestaurantsResponse : ArrayList<EatsRestaurantsResponseItem>()
data class EatsRestaurantsResponseItem(
    val cover_img_url: String,
    val delivery_fee: Int,
    val description: String,
    val id: Int,
    val name: String,
    val status: String
)