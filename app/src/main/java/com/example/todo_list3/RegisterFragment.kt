package com.example.todo_list3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController // Importar para navegação

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate o layout para este fragmento
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDoRegister = view.findViewById<Button>(R.id.btnDoRegister)
        val btnBackToLogin = view.findViewById<Button>(R.id.btnBackToLogin)

        btnDoRegister.setOnClickListener {
            // Lógica para realizar o cadastro.
            // Após o cadastro, você pode navegar de volta para a tela de login
            // ou ir direto para a MainActivity da ToDoList, dependendo da sua lógica.
            findNavController().popBackStack() // Volta para a tela anterior (Login)
        }

        btnBackToLogin.setOnClickListener {
            findNavController().popBackStack() // Volta para a tela de login
        }
    }
}