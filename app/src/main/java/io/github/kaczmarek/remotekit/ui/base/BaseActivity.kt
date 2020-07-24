package io.github.kaczmarek.remotekit.ui.base

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatActivity
import moxy.MvpView

interface BaseView : MvpView

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) :
    MvpAppCompatActivity(contentLayoutId), BaseView