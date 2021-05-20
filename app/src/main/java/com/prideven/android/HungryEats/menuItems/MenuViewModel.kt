package com.prideven.android.hungryeats.menuitems

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prideven.android.hungryeats.HungryEatsApplication
import com.prideven.android.hungryeats.menuitems.MenuViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : ViewModel() {
    var hungryEatsApplication = HungryEatsApplication.getInstance()

    // Create a LiveData with a String
    private var eatsMenuResponse: MutableLiveData<EatsMenuResponse?>? = null
    val menuDetails: MutableLiveData<EatsMenuResponse?>
        get() {
            if (eatsMenuResponse == null) {
                eatsMenuResponse = MutableLiveData()
            }
            return eatsMenuResponse!!
        }

    fun fetchMenuList(
        responseCallback: Callback<EatsMenuResponse>?,
        restaurant_id: Int
    ) {
        val retrofit = hungryEatsApplication.retrofitInstance
        val eatsMenuWebService: MenuWebService = retrofit.create(MenuWebService::class.java)
        val call: Call<EatsMenuResponse> = eatsMenuWebService.getMenu(restaurant_id)
        call.enqueue(responseCallback)
    }

    fun callMenuDataRepo(restaurant_id: Int) {
        val `object`: Callback<EatsMenuResponse> =
            object : Callback<EatsMenuResponse?> {
                override fun onResponse(
                    call: Call<EatsMenuResponse?>,
                    response: Response<EatsMenuResponse?>
                ) {
                    Log.i("", "sucessful")
                    eatsMenuResponse!!.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<EatsMenuResponse?>,
                    throwable: Throwable
                ) {
                    Log.e(TAG, throwable.toString())
                }
            }
        fetchMenuList(`object`, restaurant_id)
    }

    companion object {
        // TODO: Implement the ViewModel
        val TAG = MenuViewModel::class.java.simpleName
    }
}