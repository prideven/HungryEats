package com.prideven.android.hungryeats

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HungryEatsApplication private constructor() : Application() {
    // set your desired log level
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor()
                // set your desired log level
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }

    companion object {
        val TAG = HungryEatsApplication::class.java.simpleName
        const val BASE_URL = "https://api.doordash.com/"
        var retrofit: Retrofit? = null
        private var restaurantDataRepository: HungryEatsApplication? = null
        val instance: HungryEatsApplication?
            get() {
                if (restaurantDataRepository == null) restaurantDataRepository =
                    HungryEatsApplication()
                return restaurantDataRepository
            }
    }
}