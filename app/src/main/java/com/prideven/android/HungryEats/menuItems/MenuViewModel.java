package com.prideven.android.hungryeats.menuitems;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prideven.android.hungryeats.HungryEatsApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    static final String TAG = MenuViewModel.class.getSimpleName();
    HungryEatsApplication hungryEatsApplication = HungryEatsApplication.getInstance();
    // Create a LiveData with a String
    private MutableLiveData<EatsMenuResponse> eatsMenuResponse;

    public MutableLiveData<EatsMenuResponse> getMenuDetails() {
        if (eatsMenuResponse == null) {
            eatsMenuResponse = new MutableLiveData<EatsMenuResponse>();
        }
        return eatsMenuResponse;
    }

    public void fetchMenuList(Callback<EatsMenuResponse> responseCallback,int restaurant_id) {
        Retrofit retrofit=hungryEatsApplication.getRetrofitInstance();
        MenuWebService eatsMenuWebService = retrofit.create(MenuWebService.class);
        Call<EatsMenuResponse> call = eatsMenuWebService.getMenu(restaurant_id);
        call.enqueue(responseCallback);
    }

    public void callMenuDataRepo(int restaurant_id) {

        Callback<EatsMenuResponse> object = new Callback<EatsMenuResponse>() {
            @Override
            public void onResponse(Call<EatsMenuResponse> call, Response<EatsMenuResponse> response) {
                Log.i("", "sucessful");
                eatsMenuResponse.postValue(response.body());

            }
            @Override
            public void onFailure(Call<EatsMenuResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        };
        fetchMenuList(object,restaurant_id);
    }
}
