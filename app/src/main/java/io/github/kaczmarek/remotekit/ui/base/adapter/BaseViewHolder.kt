package io.github.kaczmarek.remotekit.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bind() {}
}