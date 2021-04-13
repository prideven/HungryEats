package com.prideven.android.hungryeats.menuitems;

import com.prideven.android.hungryeats.EatsRestaurantsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MenuWebService {


    @GET("v2/restaurant/")
    Call<EatsRestaurantsResponse> getRestaurants(@Query("restaurant_id") int restuarant_id) ;
}


