package io.github.kaczmarek.remotekit.ui.main

import androidx.fragment.app.Fragment

interface OnNavigateListener {

    fun onNavigate(
        fragmentInstance: Fragment,
        tag: String?
    )
}