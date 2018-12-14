package com.osaat.fruity.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class FruitsResponse: Serializable {

    @SerializedName("fruit")
    @Expose
    lateinit var fruit: List<FruitItem>
}
