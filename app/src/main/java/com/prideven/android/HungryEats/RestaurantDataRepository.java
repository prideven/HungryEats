package com.prideven.android.hungryeats;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestaurantDataRepository {

    static final String TAG = RestaurantDataRepository.class.getSimpleName();
    static final String BASE_URL = "https://documenu.p.rapidapi.com/";
    static Retrofit retrofit = null;
    final static String x_api_key = "465902f3f1e54a8b2ae8c350d55b8966";
    final static String x_rapidapi_key = "3e01ea9d4emsh8eef2e4cf6fcb18p1c7675jsn07b6b989bf1e";
    final static String x_rapidapi_host = "documenu.p.rapidapi.com";
    private static RestaurantDataRepository restaurantDataRepository = null;

    private RestaurantDataRepository() {
        //Singleton instance created for the class
    }

    public static RestaurantDataRepository getInstance() {
        if (restaurantDataRepository == null)
            restaurantDataRepository = new RestaurantDataRepository();
        return restaurantDataRepository;
    }

    public void fetchRestaurantList(Callback<RestaurantsResponse> responseCallback, int zipCode) {

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

        RestaurantsWebService restaurantsWebService = retrofit.create(RestaurantsWebService.class);
        Call<RestaurantsResponse> call = restaurantsWebService.getRestaurants(x_api_key, x_rapidapi_key, x_rapidapi_host,zipCode);
        call.enqueue(responseCallback);
    }

}
