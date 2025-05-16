package com.example.projetonovoecopla.ui.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.databinding.FragmentTelaLoginBinding

class TelaLoginFragment : Fragment() {

    private var _binding: FragmentTelaLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaLoginBinding.inflate(inflater, container, false)

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.btnRegistrarTopo.setOnClickListener {
            // Navega para a tela de registro
            findNavController().navigate(com.example.projetonovoecopla.R.id.telaRegistroFragment)
        }

        binding.btnLoginEnviar.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val senha = binding.etSenha.text.toString()

            if (email.isBlank() || senha.isBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                // Lógica para autenticação
                Toast.makeText(requireContext(), "Fazendo login com $email", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
