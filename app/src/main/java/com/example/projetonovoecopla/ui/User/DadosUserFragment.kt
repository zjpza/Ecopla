package com.example.projetonovoecopla.ui.User

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentDadosBinding

class DadosUserFragment : Fragment() {

    private var _binding: FragmentDadosBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDadosBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.usuario.observe(viewLifecycleOwner) { usuario ->
            binding.tvName.text = "Nome: ${usuario.nome}"
            binding.tvEmail.text = "Email: ${usuario.email}"
            binding.tvEndereco.text = "Endereço: ${usuario.endereco ?: "Não informado"}"
            binding.tvCelular.text = "Celular: ${usuario.telefone ?: "Não informado"}"
            binding.tvCep.text = "CEP: ${usuario.cep ?: "Não informado"}"
        }

        viewModel.error.observe(viewLifecycleOwner) { mensagem ->
            Toast.makeText(requireContext(), mensagem, Toast.LENGTH_SHORT).show()
        }

        viewModel.carregarUsuarios()

        binding.botaoVoltarUser.setOnClickListener {
            findNavController().navigate(R.id.navigation_user)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
