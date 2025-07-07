package com.example.todo_list3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list3.databinding.FragmentTodoListBinding

import androidx.fragment.app.Fragment // Importar Fragment
import android.view.LayoutInflater // Importar LayoutInflater
import android.view.View // Importar View
import android.view.ViewGroup // Importar ViewGroup

import androidx.navigation.fragment.findNavController

class ToDoListFragment : Fragment(), TaskItemClickListener {

   private lateinit var binding: FragmentTodoListBinding
   private lateinit var taskViewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
//        setContentView(binding.root)
//
//        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
//
//        binding.activityMainBtnNovaTarefa.setOnClickListener{
//            NewTaskSheetFragment(null).show(supportFragmentManager, "newTaskTag" )
//        }
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
//
//        setRecyclerView()
//    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    // ViewModelProvider(this) agora se refere ao Fragment.
    // Se o ViewModel precisa ser compartilhado entre Activity e Fragment,
    // ou ter um escopo maior, você pode usar ViewModelProvider(requireActivity())
   // taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

    taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

    binding.activityMainBtnNovaTarefa.setOnClickListener{
        // O show do DialogFragment precisa do childFragmentManager do Fragment
        NewTaskSheetFragment(null).show(childFragmentManager, "newTaskTag" )
    }

    // NOVO: Listener para o botão de Logout
    binding.btnLogout.setOnClickListener {
        // Navega para o LoginFragment
        findNavController().navigate(R.id.loginFragment, null,
            // Opções de navegação para limpar a back stack
            androidx.navigation.navOptions {
                popUpTo(R.id.nav_graph) {
                    inclusive = true // Remove todos os destinos da back stack
                }
            }
        )
    }


    // ViewCompat.setOnApplyWindowInsetsListener geralmente é feito na Activity principal
    // ou no layout raiz do Fragment se ele for a tela principal.
    // Se a MainActivity (nova) já lida com isso, você pode remover daqui.
    // Para simplificar, vamos remover esta parte por agora, pois a nova MainActivity cuidará dos insets.
    // ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
    //     val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    //     v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
    //     insets
    // }

    setRecyclerView()
}



    private fun setRecyclerView(){

        val fragmentContext = requireContext()

        taskViewModel.taskItems.observe(viewLifecycleOwner){
            binding.todoListRecyclerView.apply {

                layoutManager = LinearLayoutManager(fragmentContext)
                adapter = TaskItemAdapter(it, this@ToDoListFragment)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        NewTaskSheetFragment(taskItem).show(childFragmentManager, "newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {
        taskViewModel.setCompleted(taskItem)
    }
}