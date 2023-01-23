package com.aman.roomwithlivedata.repository

import androidx.lifecycle.LiveData
import com.aman.roomwithlivedata.models.Task
import com.aman.roomwithlivedata.room.TaskDao

class TaskRepository(private val taskDao: TaskDao) {
    val readAllData:LiveData<List<Task>> = taskDao.getTasks()

    suspend fun insertTask(user: Task){
        taskDao.insertTask(user)
    }
}