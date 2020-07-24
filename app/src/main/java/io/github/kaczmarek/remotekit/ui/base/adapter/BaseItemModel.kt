package io.github.kaczmarek.remotekit.ui.base.adapter

import androidx.annotation.IntDef
import io.github.kaczmarek.remotekit.R

const val TYPE_EMPTY_BONDED_DEVICES_LIST = R.layout.rv_empty_bonded_devices_item
const val TYPE_BONDED_DEVICE_ITEM = R.layout.rv_bonded_device_item

@IntDef(
    TYPE_EMPTY_BONDED_DEVICES_LIST,
    TYPE_BONDED_DEVICE_ITEM
)
@Retention(AnnotationRetention.SOURCE)
annotation class ItemViewType

interface ItemBase : DiffComparable {

    @ItemViewType
    val itemViewType: Int
}
