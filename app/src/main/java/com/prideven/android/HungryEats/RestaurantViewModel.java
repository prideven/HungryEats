package com.prideven.android.hungryeats;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        hungryEatsApplication.fetchRestaurantList(object,lat,lng);
    }
}



