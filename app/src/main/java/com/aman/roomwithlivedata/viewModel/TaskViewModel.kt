package com.aman.roomwithlivedata.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aman.roomwithlivedata.models.Task
import com.aman.roomwithlivedata.repository.TaskRepository
import com.aman.roomwithlivedata.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {
    val taskList:LiveData<List<Task>>

    private val repository:TaskRepository
    init {
        val taskDao=RoomDb.getDatabase(application).taskDao()
        repository= TaskRepository(taskDao)
        taskList=repository.readAllData
    }
    fun insertTask(task: Task){
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertTask(task)

        }
    }

    fun getTask(): LiveData<List<Task>> {
        return taskList
    }
}