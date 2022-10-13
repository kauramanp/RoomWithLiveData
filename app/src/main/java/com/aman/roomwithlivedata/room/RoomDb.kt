package com.aman.roomwithlivedata.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aman.roomwithlivedata.R
import com.aman.roomwithlivedata.models.Task

@Database(entities = [Task::class], version = 1)
abstract class RoomDb : RoomDatabase(){
    abstract fun notesDao(): TaskDao
    companion object{
        var notesDatabase: RoomDb?= null
        @Synchronized
        fun getDatabase(context: Context): RoomDb{
            if(notesDatabase == null){
                notesDatabase =  Room.databaseBuilder(
                    context,
                    RoomDb::class.java, context.resources.getString(R.string.app_name)
                ).build()
            }
            return notesDatabase!!
        }
    }
}