package com.osaat.fruity.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class FruitItem: Serializable {

    @SerializedName("type")
    @Expose
    lateinit var type: String

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("weight")
    @Expose
    var weight: Int? = null
}
