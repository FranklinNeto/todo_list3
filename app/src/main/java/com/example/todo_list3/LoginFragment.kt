package com.example.todo_list3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController // Importar para navegação

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate o layout para este fragmento
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {
            // Lógica de autenticação aqui.
            // Por enquanto, vamos direto para a MainActivity da ToDoList.
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish() // Finaliza a atividade de login para não voltar a ela
        }

        btnRegister.setOnClickListener {
            // Navega para o fragmento de cadastro
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}