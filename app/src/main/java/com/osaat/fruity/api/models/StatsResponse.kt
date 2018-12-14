package com.osaat.fruity.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class StatsResponse: Serializable {

    @SerializedName("stats")
    @Expose
    lateinit var stats: Any
}
