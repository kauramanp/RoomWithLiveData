package com.aman.roomwithlivedata.ui

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.roomwithlivedata.R
import com.aman.roomwithlivedata.adapters.ListAdapter
import com.aman.roomwithlivedata.databinding.ActivityMainBinding
import com.aman.roomwithlivedata.databinding.DialogAddUpdateBinding
import com.aman.roomwithlivedata.models.Task
import com.aman.roomwithlivedata.viewModel.TaskViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var taskList = ArrayList<Task>()
    lateinit var listAdapter : ListAdapter
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listAdapter = ListAdapter(this)
        binding.listView.layoutManager = LinearLayoutManager(this)
        binding.listView.adapter = listAdapter

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.getTask().observe(this
        ) { t ->
            taskList.clear()
            taskList.addAll(t as ArrayList<Task>)
            listAdapter.updateList(taskList)
            listAdapter.notifyDataSetChanged()
        }

        binding.fabAddUpdate.setOnClickListener {
            showDialog()
        }

    }

    fun showDialog(task: Task = Task(), position: Int = -1){
        var dialogBinding = DialogAddUpdateBinding.inflate(layoutInflater)
        var dialog = Dialog(this)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.task = task
        if(position<0){
            dialogBinding.btnAdd.setText(resources.getString(R.string.add))
        }
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogBinding.etTask.doOnTextChanged { text, start, before, count ->
            if((text?.length?:0)>0){
                dialogBinding.tilTask.isErrorEnabled = false
            }
        }
        dialogBinding.etDate.doOnTextChanged { text, start, before, count ->
            if((text?.length?:0)>0){
                dialogBinding.tilDate.isErrorEnabled = false
            }
        }
        dialogBinding.btnAdd.setOnClickListener {
            if(dialogBinding.etTask.text.isNullOrEmpty()){
                dialogBinding.tilTask.error = resources.getString(R.string.enter_task)
                dialogBinding.tilTask.isErrorEnabled = true
                dialogBinding.etTask.requestFocus()
                return@setOnClickListener
            }else if(dialogBinding.etDate.text.isNullOrEmpty()){
                dialogBinding.tilDate.isErrorEnabled = true
                dialogBinding.tilDate.error = resources.getString(R.string.enter_date)
                dialogBinding.etDate.requestFocus()
                return@setOnClickListener
            }else if(position == -1){
                task.task = dialogBinding.etTask.text.toString()
                task.date = dialogBinding.etDate.text.toString()
                taskViewModel.insertTask(task)

            }else{
               
                }
           // adapter.updateList(mainActivity.menuItem)

            dialog.dismiss()
        }
        dialog.show()

    }

}