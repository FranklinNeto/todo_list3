package com.example.todo_list3

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskItem(

    var nameItem:String,
    var descItem:String,
    var dueTimeItem: LocalTime?,
    var completedDateItem: LocalDate?,
    var idItem: UUID = UUID.randomUUID()

) {



}