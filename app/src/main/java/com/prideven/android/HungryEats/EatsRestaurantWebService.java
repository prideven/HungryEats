package com.prideven.android.hungryeats;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EatsRestaurantWebService {

    @GET("v2/restaurant/")
    Call<EatsRestaurantsResponse> getRestaurants(@Query("lat") Double latitude, @Query("lng") Double longitude);
}
