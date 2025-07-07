package com.example.todo_list3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo_list3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.activityMainBtnNovaTarefa.setOnClickListener{
            NewTaskSheetFragment().show(supportFragmentManager, "newTaskTag" )

            // NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }

        taskViewModel.name.observe(this){

            binding.activityMainNomeTarefa.text = String.format("Nome da Tarefa: %s", it)
        }

        taskViewModel. description.observe(this){

            binding.activityMainDescricaoTarefa.text = String.format("Descrição: %s", it)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
}