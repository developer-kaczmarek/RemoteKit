package io.github.kaczmarek.remotekit.ui.bondedDevices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import io.github.kaczmarek.remotekit.R
import io.github.kaczmarek.remotekit.ui.base.adapter.*
import kotlinx.android.synthetic.main.rv_bonded_device_item.view.*
import kotlinx.android.synthetic.main.rv_empty_bonded_devices_item.view.*

class BondedDevicesRVAdapter : BaseListAdapter<ItemBase, BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            TYPE_BONDED_DEVICE_ITEM -> BondedDeviceViewHolder(view)
            else -> EmptyListBondedDevicesViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bind()
        }
    }

    inner class EmptyListBondedDevicesViewHolder(view: View) : BaseViewHolder(view),
        View.OnClickListener {
        private val title = view.tv_title
        private val description = view.tv_description
        private val action = view.tv_action
        private lateinit var item: ItemEmptyList

        init {
            action.setOnClickListener(this)
        }

        override fun bind() {
            super.bind()
            item = getItem(adapterPosition) as ItemEmptyList
            title.setText(item.titleId)
            description.setText(item.messageId)
            when (item.reason) {
                TypeReason.BLUETOOTH_IS_DISABLED -> action.setText(R.string.fragment_bonded_devices_turn_on_bluetooth)
                TypeReason.NOT_BONDED_DEVICES -> action.setText(R.string.fragment_bonded_devices_go_to_settings)
                else -> action.visibility = View.GONE
            }
        }

        override fun onClick(v: View) {
            listener?.onClick(v, item)
        }
    }

    inner class BondedDeviceViewHolder(view: View) : BaseViewHolder(view), View.OnClickListener {

        private val name = view.tv_name
        private val macAddress = view.tv_mac_address
        private val typeDevice = view.iv_type_device
        private lateinit var item: ItemBondedDevice

        override fun onClick(v: View) {
            listener?.onClick(v, item)
        }

        override fun bind() {
            super.bind()
            val item = getItem(adapterPosition) as ItemBondedDevice
            name.text = item.name
            macAddress.text = item.mac
            when (item.typeDevice) {
                256 -> {
                    typeDevice.setColorFilter(
                        ContextCompat.getColor(itemView.context, R.color.colorTintComputerDevice),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    typeDevice.background.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorBackgroundComputerDevice
                        ), android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
                512 -> {
                    typeDevice.setColorFilter(
                        ContextCompat.getColor(itemView.context, R.color.colorTintSmartphoneDevice),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    typeDevice.background.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorBackgroundSmartphoneDevice
                        ), android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
                else -> {
                    typeDevice.setColorFilter(
                        ContextCompat.getColor(itemView.context, R.color.colorTintOtherDevice),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                    typeDevice.background.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorBackgroundOtherDevice
                        ), android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }
    }
}