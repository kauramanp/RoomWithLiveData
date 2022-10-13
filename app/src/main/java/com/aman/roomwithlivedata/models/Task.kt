package com.aman.roomwithlivedata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "task")
    var task: String?= null

    @ColumnInfo(name = "date")
    var date: String?= null
}