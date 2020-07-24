package io.github.kaczmarek.remotekit.ui.splash

import io.github.kaczmarek.remotekit.ui.base.BasePresenter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.presenterScope

class SplashPresenter : BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        startApplication()
    }

    private fun startApplication() {
        presenterScope.launch {
            try {
                delay(5000)
                viewState.showNextFragment()
            } catch (e: Exception) {

            }
        }
    }

    companion object {
        private const val TAG = "SplashPresenter"
    }
}