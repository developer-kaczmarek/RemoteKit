package io.github.kaczmarek.remotekit.ui.base

import moxy.MvpPresenter

open class BasePresenter<View : BaseView> : MvpPresenter<View>()