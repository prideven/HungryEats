package com.prideven.android.hungryeats.menuitems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuWebService {


    @GET("v2/restaurant/{id}/")
    Call<EatsMenuResponse> getMenu(@Path("id") int restaurant_id);

}


