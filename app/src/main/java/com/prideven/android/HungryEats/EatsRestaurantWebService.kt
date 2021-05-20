package com.prideven.android.hungryeats

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EatsRestaurantWebService {
    @GET("v2/restaurant/")
    fun getRestaurants(
        @Query("lat") latitude: Double?,
        @Query("lng") longitude: Double?
    ): Call<EatsRestaurantsResponse?>?
}