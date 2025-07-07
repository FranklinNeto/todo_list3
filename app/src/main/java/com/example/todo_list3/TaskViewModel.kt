package com.example.todo_list3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel: ViewModel() {

   var taskItems = MutableLiveData<MutableList<TaskItem>>()


  init{

     taskItems.value = mutableListOf()
  }

   fun addTaskItem(newTask:TaskItem){
      val list = taskItems.value
      list!!.add(newTask)
      taskItems.postValue(list)
   }

   fun updateTaskItem(id: UUID, name:String, desc: String, dueTime: LocalTime?){
      val list = taskItems.value
      val task = list!!.find {it.idItem == id}!!

      task.nameItem = name
      task.descItem = desc
      task.dueTimeItem = dueTime

      taskItems.postValue(list)
   }

   fun setCompleted(taskItem: TaskItem){

      val list = taskItems.value
      val task = list!!.find {it.idItem == taskItem.idItem}!!

      if (task.completedDateItem == null)
         taskItem.completedDateItem = LocalDate.now()


      taskItems.postValue(list)

   }
}
