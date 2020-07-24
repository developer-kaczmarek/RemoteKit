package io.github.kaczmarek.remotekit.ui.main

import io.github.kaczmarek.remotekit.ui.base.BasePresenter

class MainPresenter : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onFirstOpen()
    }
}