package com.prideven.android.hungryeats;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestaurantViewModel extends ViewModel {

    static final String TAG = RestaurantViewModel.class.getSimpleName();


    HungryEatsApplication hungryEatsApplication = HungryEatsApplication.getInstance();


    // Create a LiveData with a String
    private MutableLiveData<List<EatsRestaurantsResponseItem>> eatsRestaurantResponse;

    public MutableLiveData<List<EatsRestaurantsResponseItem>> getRestaurantDetails() {
        if (eatsRestaurantResponse == null) {
            eatsRestaurantResponse = new MutableLiveData<List<EatsRestaurantsResponseItem>>();
        }
        return eatsRestaurantResponse;
    }


    public void fetchRestaurantList(Callback<EatsRestaurantsResponse> responseCallback, double lat, double lng) {
        Retrofit retrofit=hungryEatsApplication.getRetrofitInstance();
        EatsRestaurantWebService eatsRestaurantsWebService = retrofit.create(EatsRestaurantWebService.class);
        Call<EatsRestaurantsResponse> call = eatsRestaurantsWebService.getRestaurants(lat,lng);
        call.enqueue(responseCallback);
    }

    public void callRestaurantDataRepo(double lat,double lng) {

        Callback<EatsRestaurantsResponse> object = new Callback<EatsRestaurantsResponse>() {
            @Override
            public void onResponse(Call<EatsRestaurantsResponse> call, Response<EatsRestaurantsResponse> response) {
                Log.i("", "sucessful");
                eatsRestaurantResponse.postValue(response.body());

            }
            @Override
            public void onFailure(Call<EatsRestaurantsResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        };
        fetchRestaurantList(object,lat,lng);
    }
}



