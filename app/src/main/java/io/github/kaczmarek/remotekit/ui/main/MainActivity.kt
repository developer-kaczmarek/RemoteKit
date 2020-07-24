package io.github.kaczmarek.remotekit.ui.main

import androidx.fragment.app.Fragment
import io.github.kaczmarek.remotekit.R
import io.github.kaczmarek.remotekit.ui.base.BaseActivity
import io.github.kaczmarek.remotekit.ui.base.BaseView
import io.github.kaczmarek.remotekit.ui.splash.SplashFragment
import io.github.kaczmarek.remotekit.utils.replaceFragment
import moxy.ktx.moxyPresenter

interface MainView : BaseView {
    fun onFirstOpen()
}

class MainActivity : BaseActivity(R.layout.activity_main), MainView, OnNavigateListener {

    private val presenter by moxyPresenter { MainPresenter() }

    override fun onFirstOpen() {
        onNavigate(SplashFragment(), SplashFragment.TAG)
    }

    override fun onNavigate(fragmentInstance: Fragment, tag: String?) {
        supportFragmentManager.replaceFragment(R.id.fl_main_container, fragmentInstance, tag)
    }
}