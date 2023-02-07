package com.aman.roomwithlivedata.interfaces

import com.aman.roomwithlivedata.models.Task

interface ClickInterface {

    fun clickInterface(data: Task, type:Int = 0, position:Int)
}