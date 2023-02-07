package com.aman.roomwithlivedata.repository

import androidx.lifecycle.LiveData
import com.aman.roomwithlivedata.models.Task
import com.aman.roomwithlivedata.room.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val readAllData:LiveData<List<Task>> = taskDao.getTasks()

    suspend fun insertTask(task: Task){
        taskDao.insertTask(task)
    }
    suspend fun updateTask(task: Task){
        taskDao.updateFun(task)
    }

    suspend fun removeTask(task: Task){
        taskDao.deleteTask(task)
    }
}