package com.prideven.android.hungryeats.menuitems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MenuWebService {


    @GET("v2/restaurant/")
    Call<EatsMenuResponse> getMenu(@Query("restaurant_id") int restuarant_id) ;
}


