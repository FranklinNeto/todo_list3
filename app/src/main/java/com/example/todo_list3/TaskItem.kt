package com.example.todo_list3

import java.time.LocalTime
import java.util.UUID

class TaskItem(

    var nameItem:String,
    var descItem:String,
    var dueTime: LocalTime?,
    var completedDate: LocalTime?,
    var idItem: UUID = UUID.randomUUID()

) {



}