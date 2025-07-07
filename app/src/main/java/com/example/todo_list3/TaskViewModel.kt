package com.example.todo_list3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

   var taskItems: MutableLiveData<MutableList<TaskItem>>()


   var name = MutableLiveData<String>()
   var description = MutableLiveData<String>()


}
