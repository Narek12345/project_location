package com.newapp.post_script.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T>(val chooseListener: ClickListener<T>) : RecyclerView.Adapter<BaseListAdapter.ViewHolder<T>>() {
    var list: List<T>? = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setNewList(objectList: List<T>?) {
        list = objectList
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return  list!!.get(position)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        val item = list?.get(position) ?: throw IllegalStateException("Unknown view")
        holder.bind(item,chooseListener)
    }

    override fun getItemCount() =  list?.size ?: 0

    abstract class ViewHolder<T>(open val binding:ViewBinding) : RecyclerView.ViewHolder(binding.root){
        abstract fun bind(item:T, chooseListener: ClickListener<T>)
    }


    interface ClickListener<T> {
        fun click(item: T)
    }
}