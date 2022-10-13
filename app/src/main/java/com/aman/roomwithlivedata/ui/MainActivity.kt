package com.aman.roomwithlivedata.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aman.roomwithlivedata.adapters.ListAdapter
import com.aman.roomwithlivedata.databinding.ActivityMainBinding
import com.aman.roomwithlivedata.models.Task

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var taskList = ArrayList<Task>()
    lateinit var listAdapter : ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listAdapter = ListAdapter(taskList)
        binding.listView.adapter = listAdapter

    }
}