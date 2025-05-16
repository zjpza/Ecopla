package com.example.projetonovoecopla.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentCarrinhoBinding
import com.example.projetonovoecopla.ui.Carrinho.CarrinhoViewModel

class CarrinhoFragment : Fragment() {

    private var _binding: FragmentCarrinhoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val carrinhoViewModel = ViewModelProvider(this)[CarrinhoViewModel::class.java]

        _binding = FragmentCarrinhoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textSubtotal: TextView = binding.tvSubtotal
        val textFrete: TextView = binding.tvFrete
        val textTotal: TextView = binding.tvTotal

        // Observa o texto vindo do ViewModel (exemplo, pode usar LiveData para atualizar os valores)
        carrinhoViewModel.subtotal.observe(viewLifecycleOwner) {
            textSubtotal.text = it
        }
        carrinhoViewModel.frete.observe(viewLifecycleOwner) {
            textFrete.text = it
        }
        carrinhoViewModel.total.observe(viewLifecycleOwner) {
            textTotal.text = it
        }

        // Botão voltar
        binding.btnVoltar.setOnClickListener {
            findNavController().navigateUp() // Volta para a tela anterior
        }

        // Botão de continuar (exemplo: enviar para próxima etapa)
        binding.btnContinuar.setOnClickListener {
            findNavController().navigate(R.id.pagamentoFragment)
        }

        return root
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
