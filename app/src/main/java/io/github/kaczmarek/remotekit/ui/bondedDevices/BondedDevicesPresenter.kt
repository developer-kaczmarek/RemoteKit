package io.github.kaczmarek.remotekit.ui.bondedDevices

import android.bluetooth.BluetoothAdapter
import io.github.kaczmarek.remotekit.R
import io.github.kaczmarek.remotekit.ui.base.BasePresenter
import io.github.kaczmarek.remotekit.ui.base.adapter.ItemBase
import kotlinx.coroutines.launch
import moxy.presenterScope

class BondedDevicesPresenter : BasePresenter<BondedDevicesView>() {
    val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

    private val bondedDevices = arrayListOf<ItemBase>()

    fun getBondedDevices() {
        presenterScope.launch {
            try {
                bluetoothAdapter?.let {
                    bondedDevices.clear()
                    if (it.isEnabled) {
                        if (it.bondedDevices.isNotEmpty()) {
                            it.bondedDevices.forEach { bluetoothDevice ->
                                bondedDevices.add(
                                    ItemBondedDevice(
                                        bluetoothDevice.name,
                                        bluetoothDevice.address,
                                        bluetoothDevice.bluetoothClass.majorDeviceClass
                                    )
                                )
                            }
                        } else {
                            bondedDevices.add(
                                ItemEmptyList(
                                    R.string.error_empty_list_title,
                                    R.string.error_not_bonded_device_description,
                                    TypeReason.NOT_BONDED_DEVICES
                                )
                            )
                        }
                    } else {
                        bondedDevices.add(
                            ItemEmptyList(
                                R.string.error_empty_list_title,
                                R.string.error_bluetooth_is_disabled_description,
                                TypeReason.BLUETOOTH_IS_DISABLED
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                bondedDevices.add(
                    ItemEmptyList(
                        R.string.error_empty_list_title,
                        R.string.error_unknown_description,
                        TypeReason.ERROR
                    )
                )
            } finally {
                viewState.onUpdateAdapter(bondedDevices)
            }
        }
    }
}