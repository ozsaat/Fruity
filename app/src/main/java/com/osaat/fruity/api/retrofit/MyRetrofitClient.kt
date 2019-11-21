package com.osaat.fruity.api.retrofit

import com.google.gson.GsonBuilder
import com.osaat.fruity.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitClient {

    private val restAdapter: Retrofit

    init {
        val builder = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .validateEagerly(true)
        restAdapter = builder.build()
    }

    fun <T> api(service: Class<T>): T {
        return restAdapter.create(service)
    }
}
