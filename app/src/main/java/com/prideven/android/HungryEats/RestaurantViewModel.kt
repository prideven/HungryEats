package com.prideven.android.hungryeats

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantViewModel : ViewModel() {
    var hungryEatsApplication = HungryEatsApplication.instance

    // Create a LiveData with a String
    private var eatsRestaurantResponse: MutableLiveData<List<EatsRestaurantsResponseItem>?>? =
        null

    val restaurantDetails: MutableLiveData<List<EatsRestaurantsResponseItem>?>
        get() {
            if (eatsRestaurantResponse == null) {
                eatsRestaurantResponse =
                    MutableLiveData()
            }
            return eatsRestaurantResponse!!
        }

    fun fetchRestaurantList(
        responseCallback: Callback<EatsRestaurantsResponse?>?,
        lat: Double,
        lng: Double
    ) {
        val retrofit = hungryEatsApplication.retrofitInstance
        val eatsRestaurantsWebService =
            retrofit.create(
                EatsRestaurantWebService::class.java
            )
        val call =
            eatsRestaurantsWebService.getRestaurants(lat, lng)
        call!!.enqueue(responseCallback)
    }

    fun callRestaurantDataRepo(lat: Double, lng: Double) {
        val `object`: Callback<EatsRestaurantsResponse?> =
            object : Callback<EatsRestaurantsResponse?> {
                override fun onResponse(
                    call: Call<EatsRestaurantsResponse?>,
                    response: Response<EatsRestaurantsResponse?>
                ) {
                    Log.i("", "sucessful")
                    eatsRestaurantResponse!!.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<EatsRestaurantsResponse?>,
                    throwable: Throwable
                ) {
                    Log.e(TAG, throwable.toString())
                }
            }
        fetchRestaurantList(`object`, lat, lng)
    }

    companion object {
        val TAG = RestaurantViewModel::class.java.simpleName
    }
}