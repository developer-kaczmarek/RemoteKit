package io.github.kaczmarek.remotekit.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import moxy.MvpAppCompatFragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) :
    MvpAppCompatFragment(contentLayoutId) {

    private var baseActivity: BaseActivity? = null

    var onBackPressedAction: (() -> Unit)? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as? BaseActivity
    }
}