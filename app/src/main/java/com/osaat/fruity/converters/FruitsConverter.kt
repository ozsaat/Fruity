package com.osaat.fruity.converters

import com.osaat.fruity.api.models.FruitItem
import com.osaat.fruity.viewmodels.FruitItemViewModel

class FruitsConverter {

    fun map(response: List<FruitItem>): List<FruitItemViewModel> =
            response.map { FruitItemViewModel(it.type, it.price, it.weight) }
}
