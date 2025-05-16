package com.example.projetonovoecopla.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentAgradecimentoPedidoBinding

class AgradecimentoPedidoFragment : Fragment() {

    private var _binding: FragmentAgradecimentoPedidoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgradecimentoPedidoBinding.inflate(inflater, container, false)

        binding.botaoVoltarPedido.setOnClickListener {
            findNavController().navigate(R.id.navigation_home) // ou outro ID da Home no seu nav_graph
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
