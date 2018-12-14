package com.osaat.fruity.mvp

import com.osaat.fruity.services.FruitsService
import io.reactivex.disposables.CompositeDisposable

class HomePresenterImp constructor(
    private var view: HomePresenter.View,
    private val fruitsService: FruitsService,
    private val disposables: CompositeDisposable = CompositeDisposable()
) : HomePresenter {

    override fun fetchFruits() {
        view.showLoader(true)
        disposables.add(fruitsService.fetchFruitsList()
            .doFinally { view.showLoader(false) }
            .subscribe(
                { response -> view.showFruitsResult(response) },
                { error -> view.showError(error.message!!) })

        )
    }

    override fun destroy() {
        disposables.dispose()
    }
}
