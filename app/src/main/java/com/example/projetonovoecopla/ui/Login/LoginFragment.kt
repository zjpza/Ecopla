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

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|com\\.br)\$")
        return email.matches(emailRegex)
    }

    private fun setupListeners() {
        binding.btnRegistrarTopo.setOnClickListener {
            findNavController().navigate(com.example.projetonovoecopla.R.id.telaRegistroFragment)
        }

        binding.btnLoginEnviar.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val senha = binding.etSenha.text.toString()

            if (email.isBlank() || senha.isBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
            else if (!isValidEmail(email)){
                Toast.makeText(requireContext(), "Email incorreto", Toast.LENGTH_SHORT).show()
            }

            else {
                // Lógica para autenticação
                Toast.makeText(requireContext(), "Fazendo login com $email", Toast.LENGTH_SHORT).show()
                findNavController().navigate(com.example.projetonovoecopla.R.id.navigation_home)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
