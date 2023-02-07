package com.aman.roomwithlivedata.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.roomwithlivedata.R
import com.aman.roomwithlivedata.adapters.ListAdapter
import com.aman.roomwithlivedata.databinding.ActivityMainBinding
import com.aman.roomwithlivedata.databinding.DialogAddUpdateBinding
import com.aman.roomwithlivedata.interfaces.ClickInterface
import com.aman.roomwithlivedata.models.Task
import com.aman.roomwithlivedata.viewModel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var taskList = ArrayList<Task>()
    lateinit var listAdapter : ListAdapter
    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listAdapter = ListAdapter(this, object: ClickInterface{
            override fun clickInterface(data: Task, type: Int, position: Int) {
                if(type == 0) {//edit
                    showDialog(data, position = position)
                }else{
                    AlertDialog.Builder(this@MainActivity).apply {
                        setTitle(resources.getString(R.string.delete_task))
                        setMessage(resources.getString(R.string.delete_task_msg))
                        setPositiveButton(resources.getString(R.string.yes)){_,_->
                            taskViewModel.deleteTask(task = data)
                        }
                        setNegativeButton(resources.getString(R.string.no)){_,_->}
                        show()
                    }
                }
            }
        })
        binding.listView.layoutManager = LinearLayoutManager(this)
        binding.listView.adapter = listAdapter

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.taskList.observe(this
        ) { t ->
            taskList.clear()
            taskList.addAll(t as ArrayList<Task>)
            listAdapter.updateList(taskList)
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

        dialogBinding.etDate.setOnClickListener {
            val datePicker = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    dialogBinding.etDate.setText(SimpleDateFormat("dd-MM-YYYY").format(newDate.getTime()))
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()

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
                task.task = dialogBinding.etTask.text.toString()
                task.date = dialogBinding.etDate.text.toString()
                taskViewModel.updateTask(task)
                }

            dialog.dismiss()
        }
        dialog.show()

    }

}