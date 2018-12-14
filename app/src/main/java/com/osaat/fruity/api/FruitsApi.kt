package com.osaat.fruity.api

import com.osaat.fruity.api.models.FruitsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface FruitsApi {

    @GET("fmtvp/recruit-test-data/master/data.json")
    fun fruits(): Single<FruitsResponse>
}
