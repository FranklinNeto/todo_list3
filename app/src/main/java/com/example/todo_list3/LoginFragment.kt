package com.example.todo_list3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import android.widget.EditText // Adicionar para pegar o texto dos campos
import android.widget.Toast // Adicionar para mostrar mensagens

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val etUsername = view.findViewById<EditText>(R.id.etUsername)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Lógica de autenticação (exemplo simples)
            if (username == "teste" && password == "123") { // Substitua pela sua lógica real
                // Navega para o ToDoListFragment usando a ação definida no nav_graph
                findNavController().navigate(R.id.action_loginFragment_to_toDoListFragment)
            } else {
                Toast.makeText(context, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            // Navega para o fragmento de cadastro
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}