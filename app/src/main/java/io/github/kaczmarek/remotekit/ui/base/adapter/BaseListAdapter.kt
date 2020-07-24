package io.github.kaczmarek.remotekit.ui.base.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<D : ItemBase, VH : BaseViewHolder>
@JvmOverloads
constructor(differ: DiffUtil.ItemCallback<D> = DiffCallback()) : ListAdapter<D, VH>(differ) {

    var listener: BaseClickListener? = null

    fun update(list: List<D>) {
        this.submitList(ArrayList(list))
    }

    @SuppressLint("DiffUtilEquals")
    private class DiffCallback<D : ItemBase> : DiffUtil.ItemCallback<D>() {

        override fun areContentsTheSame(oldItem: D, newItem: D): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: D, newItem: D): Boolean =
            oldItem.getItemId() == newItem.getItemId()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

    override fun getItemViewType(position: Int) = getItem(position).itemViewType
}

interface DiffComparable {
    fun getItemId(): Int
}

interface BaseClickListener {
    fun onClick(view: View, model: ItemBase)
}