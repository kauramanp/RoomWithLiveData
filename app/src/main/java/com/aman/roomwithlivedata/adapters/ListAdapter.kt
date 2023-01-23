package com.aman.roomwithlivedata.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aman.roomwithlivedata.databinding.ItemLayoutBinding
import com.aman.roomwithlivedata.models.Task

class ListAdapter(var context: Context):RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var list =  ArrayList<Task>()
    inner class ViewHolder(var binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(task: Task,position: Int, ){
            binding.task= task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: ArrayList<Task>){
        this.list.clear()
        this.list.addAll(list)
    }

    fun clearList(){
        this.list.clear()
    }
}