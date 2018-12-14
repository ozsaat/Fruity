package com.osaat.fruity.viewmodels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FruitItemViewModel(val fruitType: String, val price: Int?, val weight: Int?) : Parcelable
