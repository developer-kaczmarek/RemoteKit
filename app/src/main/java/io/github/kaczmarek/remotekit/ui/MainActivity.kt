package io.github.kaczmarek.remotekit.ui

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.kaczmarek.circledpad.CircleDPad
import io.github.kaczmarek.circledpad.CircleDPad.Companion.BOTTOM_BUTTON
import io.github.kaczmarek.circledpad.CircleDPad.Companion.CENTER_BUTTON
import io.github.kaczmarek.circledpad.CircleDPad.Companion.LEFT_BUTTON
import io.github.kaczmarek.circledpad.CircleDPad.Companion.RIGHT_BUTTON
import io.github.kaczmarek.circledpad.CircleDPad.Companion.TOP_BUTTON
import io.github.kaczmarek.remotekit.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CircleDPad.OnClickCircleDPadListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cdp_controller.listener = this

        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val pairedDevices = mBluetoothAdapter.bondedDevices

        val s: MutableList<String> = ArrayList()
        for (bt in pairedDevices) s.add(bt.name)


    }

    override fun onClickButton(button: Int) {
        when (button) {
            BOTTOM_BUTTON -> {}
            LEFT_BUTTON -> {}
            TOP_BUTTON -> {}
            RIGHT_BUTTON -> {}
            CENTER_BUTTON -> {}
        }
    }
}