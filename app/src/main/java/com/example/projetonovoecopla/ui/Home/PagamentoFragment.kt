package com.example.projetonovoecopla.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.databinding.FragmentPagamentoBinding

class PagamentoFragment : Fragment() {

    private var _binding: FragmentPagamentoBinding? = null
    private val binding get() = _binding!!

    private val formasPagamento = listOf("Débito", "Crédito", "Pix", "Boleto bancário")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagamentoBinding.inflate(inflater, container, false)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, formasPagamento)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPagamento.adapter = adapter

        binding.btnCancelar.setOnClickListener {
            findNavController().popBackStack() // Volta para tela anterior
        }

        binding.btnConfirmar.setOnClickListener {
            val formaSelecionada = binding.spinnerPagamento.selectedItem.toString()
            Toast.makeText(requireContext(), "Pagamento via $formaSelecionada confirmado!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(com.example.projetonovoecopla.R.id.agradecimentoPedidoFragment)
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
