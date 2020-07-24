package io.github.kaczmarek.remotekit.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import io.github.kaczmarek.remotekit.R
import io.github.kaczmarek.remotekit.ui.main.MainActivity
import io.github.kaczmarek.remotekit.ui.main.OnNavigateListener
import io.github.kaczmarek.remotekit.ui.base.BaseFragment
import io.github.kaczmarek.remotekit.ui.base.BaseView
import io.github.kaczmarek.remotekit.ui.bondedDevices.BondedDevicesFragment
import kotlinx.android.synthetic.main.fragment_splash.*
import moxy.ktx.moxyPresenter

interface SplashView : BaseView {
    fun showNextFragment()
}

class SplashFragment : BaseFragment(R.layout.fragment_splash), SplashView {

    private val presenter by moxyPresenter { SplashPresenter() }

    private var onNavigateListener: OnNavigateListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNavigateListener = context as? MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pl_splash_container.start()
    }

    override fun onDestroyView() {
        pl_splash_container.stop()
        super.onDestroyView()
    }

    override fun showNextFragment() {
        onNavigateListener?.onNavigate(BondedDevicesFragment(), BondedDevicesFragment.TAG)
    }

    companion object {
        const val TAG = "SplashFragment"
    }
}