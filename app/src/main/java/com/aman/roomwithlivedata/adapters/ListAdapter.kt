package com.aman.roomwithlivedata.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.aman.roomwithlivedata.databinding.ItemLayoutBinding
import com.aman.roomwithlivedata.models.Task


class ListAdapter(var list: MutableList<Task>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list.size
    }

    override fun getItemId(p0: Int): Long {
        return list[p0].id.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val itemBinding: ItemLayoutBinding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent?.getContext()),
            parent,
            false
        )
        itemBinding.task = list[position]

       return itemBinding.root
    }
}