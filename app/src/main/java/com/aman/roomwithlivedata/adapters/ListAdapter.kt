package com.aman.roomwithlivedata.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aman.roomwithlivedata.databinding.ItemLayoutBinding
import com.aman.roomwithlivedata.interfaces.ClickInterface
import com.aman.roomwithlivedata.models.Task

class ListAdapter(var context: Context, var clickInterface: ClickInterface):RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var list =  ArrayList<Task>()
    private val TAG = "ListAdapter"
    inner class ViewHolder(var binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(task: Task,position: Int,clickInterface: ClickInterface ){
            Log.e(TAG," task ${task.task} $position")
            binding.task= task
            binding.click= clickInterface
            binding.position = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position],position, clickInterface = clickInterface)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: ArrayList<Task>){
        Log.e(TAG," list ${list.size}")
        this.list.clear()
        this.list.addAll(list)
        Log.e(TAG,"this list ${this.list.size}")

        notifyDataSetChanged()
    }

    fun clearList(){
        this.list.clear()
    }
}