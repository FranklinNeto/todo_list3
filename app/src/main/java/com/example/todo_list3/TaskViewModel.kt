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
        // Use 'let' para só executar o bloco se 'list' não for nulo
        taskItems.value?.let { list -> // 'list' aqui é garantido como não nulo
            list.add(newTask)
            taskItems.postValue(list)
        } ?: run {
            // Opcional: Se por algum motivo a lista ainda for nula aqui,
            // você pode criar uma nova ou logar um erro.
            // Por exemplo, para garantir que nunca seja nula, você pode inicializar:
            taskItems.value = mutableListOf(newTask)
            taskItems.postValue(taskItems.value)
        }
    }

    // Dentro de updateTaskItem:
    fun updateTaskItem(id: UUID, name:String, desc: String, dueTime: LocalTime?){
        taskItems.value?.let { list -> // Garante que a lista não é nula
            val task = list.find { it.idItem == id }
            task?.let { t -> // Garante que a tarefa foi encontrada e não é nula
                t.nameItem = name
                t.descItem = desc
                t.dueTimeItem = dueTime
                taskItems.postValue(list)
            }
        }
    }

    // Dentro de setCompleted:
    fun setCompleted(taskItem: TaskItem){
        taskItems.value?.let { list -> // Garante que a lista não é nula
            val task = list.find { it.idItem == taskItem.idItem }
            task?.let { t -> // Garante que a tarefa foi encontrada e não é nula
                if (t.completedDateItem == null)
                    t.completedDateItem = LocalDate.now()
                taskItems.postValue(list)
            }
        }
    }
}
