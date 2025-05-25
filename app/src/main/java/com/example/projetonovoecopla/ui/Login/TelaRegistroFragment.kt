package com.example.projetonovoecopla.ui.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.databinding.FragmentTelaRegistroBinding

class TelaRegistroFragment : Fragment() {

    private var _binding: FragmentTelaRegistroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaRegistroBinding.inflate(inflater, container, false)

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        binding.btnLoginTopo.setOnClickListener {

            findNavController().navigate(com.example.projetonovoecopla.R.id.telaLoginFragment)
        }

        binding.btnEnviar.setOnClickListener {
            val nome = binding.etNome.text.toString()
            val login = binding.etLogin.text.toString()
            val senha = binding.etSenha.text.toString()

            if (nome.isBlank() || login.isBlank() || senha.isBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(com.example.projetonovoecopla.R.id.navigation_home)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
