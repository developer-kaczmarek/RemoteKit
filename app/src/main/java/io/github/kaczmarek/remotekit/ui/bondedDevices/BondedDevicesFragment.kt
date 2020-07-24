package io.github.kaczmarek.remotekit.ui.bondedDevices

import android.app.Activity.RESULT_OK
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AnimationUtils
import io.github.kaczmarek.remotekit.R
import io.github.kaczmarek.remotekit.ui.main.MainActivity
import io.github.kaczmarek.remotekit.ui.main.OnNavigateListener
import io.github.kaczmarek.remotekit.ui.base.BaseFragment
import io.github.kaczmarek.remotekit.ui.base.BaseView
import io.github.kaczmarek.remotekit.ui.base.adapter.BaseClickListener
import io.github.kaczmarek.remotekit.ui.base.adapter.ItemBase
import kotlinx.android.synthetic.main.fragment_bonded_devices.*
import moxy.ktx.moxyPresenter

interface BondedDevicesView : BaseView {
    fun showNextFragment()
    fun onUpdateAdapter(list: List<ItemBase>)
}

class BondedDevicesFragment : BaseFragment(R.layout.fragment_bonded_devices), BondedDevicesView,
    BaseClickListener {
    private val presenter by moxyPresenter { BondedDevicesPresenter() }

    private var onNavigateListener: OnNavigateListener? = null

    private val rvAdapter by lazy {
        BondedDevicesRVAdapter().apply {
            listener = this@BondedDevicesFragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNavigateListener = context as? MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_bonded_devices.adapter = rvAdapter
        presenter.getBondedDevices()
    }

    override fun showNextFragment() {

    }

    override fun onClick(view: View, model: ItemBase) {
        when (model) {
            is ItemEmptyList -> {
                when (model.reason) {
                    TypeReason.BLUETOOTH_IS_DISABLED -> {
                        if (presenter.bluetoothAdapter?.isEnabled == false) {
                            startActivityForResult(
                                Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),
                                REQUEST_ENABLE_BLUETOOTH
                            )
                        } else {
                            presenter.getBondedDevices()
                        }
                    }
                    else -> startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
                }
            }
            is ItemBondedDevice -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_ENABLE_BLUETOOTH && resultCode == RESULT_OK) {
            presenter.getBondedDevices()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onUpdateAdapter(list: List<ItemBase>) {
        rv_bonded_devices.layoutAnimation = AnimationUtils.loadLayoutAnimation(
            requireContext(),
            R.anim.layout_animation
        )
        rvAdapter.update(list)
    }

    companion object {
        const val TAG = "BondedDevicesFragment"
        const val REQUEST_ENABLE_BLUETOOTH = 7421
    }
}