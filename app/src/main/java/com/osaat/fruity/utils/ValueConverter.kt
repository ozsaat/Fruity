package com.osaat.fruity.utils

class ValueConverter {

    private val penceToPounds: Double = 100.0
    private val gramsToKilos: Double = 100.0

    fun convertPriceFromPenceToPounds(priceInPence: Int): Double {
        return (priceInPence.toDouble()/penceToPounds)
    }

    fun convertWeightFromGramsToKilos(weightInGrams: Int): Double {
        return (weightInGrams.toDouble()/gramsToKilos)
    }
}
