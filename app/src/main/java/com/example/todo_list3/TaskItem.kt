package com.example.todo_list3

import android.content.Context
import androidx.core.content.ContextCompat
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

    fun isCompleted() = completedDateItem != null

    fun imageResource():Int = if (isCompleted()) R.drawable.check_box_24 else R.drawable.unchecked_box_24

    fun imageColor(context: Context): Int = if (isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)
}