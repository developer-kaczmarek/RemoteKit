package io.github.kaczmarek.remotekit.ui.bondedDevices

import androidx.annotation.StringRes
import io.github.kaczmarek.remotekit.ui.base.adapter.ItemBase
import io.github.kaczmarek.remotekit.ui.base.adapter.TYPE_BONDED_DEVICE_ITEM
import io.github.kaczmarek.remotekit.ui.base.adapter.TYPE_EMPTY_BONDED_DEVICES_LIST

data class ItemEmptyList(
    @StringRes val titleId: Int,
    @StringRes val messageId: Int,
    val reason: TypeReason = TypeReason.NOT_BONDED_DEVICES,
    override val itemViewType: Int = TYPE_EMPTY_BONDED_DEVICES_LIST
) : ItemBase {
    override fun getItemId() = hashCode()
}

data class ItemBondedDevice(
    val name: String,
    val mac: String,
    val typeDevice: Int,
    override val itemViewType: Int = TYPE_BONDED_DEVICE_ITEM
) : ItemBase {

    override fun getItemId() = mac.hashCode()
}

enum class TypeReason {
    ERROR, BLUETOOTH_IS_DISABLED, NOT_BONDED_DEVICES
}
