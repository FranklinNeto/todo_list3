package com.example.todo_list3

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list3.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener
):RecyclerView.ViewHolder(binding.root) {

    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    fun bindTaskItem(taskItem: TaskItem){


        binding.taskItemCellNome.text = taskItem.nameItem

        if (taskItem.isCompleted()){
            binding.taskItemCellNome.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.taskItemCellDueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.taskItemCellBtnComplete.setImageResource(taskItem.imageResource())
        binding.taskItemCellBtnComplete.setColorFilter(taskItem.imageColor(context))

        binding.taskItemCellBtnComplete.setOnClickListener{
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        if(taskItem.dueTimeItem != null){

            binding.taskItemCellDueTime.text = timeFormat.format(taskItem.dueTimeItem)
        }

        else{
            binding.taskItemCellDueTime.text = ""
        }
    }
}