package com.osaat.fruity.mvp

import com.osaat.fruity.viewmodels.FruitItemViewModel

interface HomePresenter {

    fun destroy()

    fun fetchFruits()

    interface View {

        fun showFruitsResult(result: List<FruitItemViewModel>)

        fun showLoader(show: Boolean)

        fun showError(message: String)
    }
}
