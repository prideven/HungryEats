package com.prideven.android.hungryeats;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface RestaurantsWebService {


    @GET("/restaurants/search/fields")
    Call<RestaurantsResponse> getRestaurants(@Header("x-api-key") String x_api_key, @Header("x-rapidapi-key") String x_rapidapi_key,
                         @Header("x-rapidapi-host") String x_rapidapi_host, @Query("zip_code") int zipCode);
}
