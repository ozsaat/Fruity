package com.osaat.fruity.api

import com.osaat.fruity.api.models.StatsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StatsApi {

    @GET("fmtvp/recruit-test-data/master/stats")
    fun stats(@Query("event") event: String, @Query("data") data: Int): Single<StatsResponse>
}
