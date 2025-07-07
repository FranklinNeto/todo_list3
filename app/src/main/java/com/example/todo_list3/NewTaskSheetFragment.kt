package com.example.todo_list3

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo_list3.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.lifecycle.ViewModelProvider
import java.time.LocalTime
import android.text.Editable



class NewTaskSheetFragment(var taskItem:TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null){

            binding.fragmentNewTaskSheetTitulo.text = "Edite a Tarefa"

            val editable = Editable.Factory.getInstance()
            binding.newTaskSheetNome.text = editable.newEditable(taskItem!!.nameItem)
            binding.newTaskSheetDescricao.text = editable.newEditable(taskItem!!.descItem)
            if(taskItem!!.dueTimeItem != null){
                dueTime = taskItem!!.dueTimeItem!!
                updateTimeButtonText()
            }
        }

        else{

            binding.fragmentNewTaskSheetTitulo.text = "Nova Tarefa"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

        binding.newTaskSheetBtnSalvarTarefa.setOnClickListener{

            saveAction()
        }

        binding.newTaskSheetBtnSelecionarTempo.setOnClickListener{

            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if(dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Tarefa Vencida")
        dialog.show()

    }


    private fun updateTimeButtonText() {
        binding.newTaskSheetBtnSelecionarTempo.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.newTaskSheetNome.text.toString()
        val desc = binding.newTaskSheetDescricao.text.toString()

        if (taskItem == null){
            val newTask = TaskItem(name, desc, dueTime, null)
            taskViewModel.addTaskItem(newTask)
        }

        else{

            taskViewModel.updateTaskItem(taskItem!!.idItem, name, desc, dueTime)
        }

        binding.newTaskSheetNome.setText("")
        binding.newTaskSheetDescricao.setText("")
        dismiss()
    }


}