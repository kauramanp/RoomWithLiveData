package com.aman.roomwithlivedata.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aman.roomwithlivedata.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Update()
    suspend fun updateFun(task: Task)

    @Delete()
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM Task")
    suspend fun deleteAll()
}