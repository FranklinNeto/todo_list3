package com.example.todo_list3

import android.os.Bundle
import androidx.fragment.app.Fragment
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
        }

        else{

            binding.fragmentNewTaskSheetTitulo.text = "Nova Tarefa"
        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

        binding.newTaskSheetBtnSalvarTarefa.setOnClickListener{

            saveAction()
        }
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
            val newTask = TaskItem(name, desc, null, null)
            taskViewModel.addTaskItem(newTask)
        }

        else{

            taskViewModel.updateTaskItem(taskItem!!.idItem, name, desc, null)
        }

        binding.newTaskSheetNome.setText("")
        binding.newTaskSheetDescricao.setText("")
        dismiss()
    }


}