package com.osaat.fruity.services

import com.osaat.fruity.api.FruitsApi
import com.osaat.fruity.converters.FruitsConverter
import com.osaat.fruity.viewmodels.FruitItemViewModel
import io.reactivex.Scheduler
import io.reactivex.Single

class FruitsService(
    private val api: FruitsApi,
    private val converter: FruitsConverter,
    private val notifications: Scheduler,
    private val worker: Scheduler
) {

    fun fetchFruitsList(): Single<List<FruitItemViewModel>> {
        return api.fruits()
            .map { response -> converter.map(response.fruit) }
            .subscribeOn(worker)
            .observeOn(notifications)
    }

}
