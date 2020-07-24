package io.github.kaczmarek.remotekit.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.replaceFragment(
    @IdRes containerId: Int,
    fragmentInstance: Fragment,
    tag: String?
) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.replace(containerId, fragmentInstance, tag)

    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
    fragmentTransaction
        .setPrimaryNavigationFragment(fragmentInstance)
        .setReorderingAllowed(true)
        .commit()
}