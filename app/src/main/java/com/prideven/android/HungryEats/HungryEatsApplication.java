package com.prideven.android.hungryeats;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HungryEatsApplication extends Application {


    static final String TAG = HungryEatsApplication.class.getSimpleName();
    static final String BASE_URL = "https://api.doordash.com/";
    static Retrofit retrofit = null;
    private static HungryEatsApplication restaurantDataRepository = null;



    private HungryEatsApplication() {
        //Singleton instance created for the class
    }

    public static HungryEatsApplication getInstance() {
        if (restaurantDataRepository == null)
            restaurantDataRepository = new HungryEatsApplication();
        return restaurantDataRepository;
    }

    public void fetchRestaurantList(Callback<EatsRestaurantsResponse> responseCallback, double lat, double lng) {

        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        EatsRestaurantWebService eatsRestaurantsWebService = retrofit.create(EatsRestaurantWebService.class);
        Call<EatsRestaurantsResponse> call = eatsRestaurantsWebService.getRestaurants(lat,lng);
        call.enqueue(responseCallback);
    }

}


