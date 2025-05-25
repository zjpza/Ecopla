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

    private lateinit var carrinhoViewModel: CarrinhoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCarrinhoBinding.inflate(inflater, container, false)

        carrinhoViewModel = ViewModelProvider(this)[CarrinhoViewModel::class.java]

        // Agora observa o LiveData da MESMA instância
        carrinhoViewModel.subtotal.observe(viewLifecycleOwner) {
            binding.tvSubtotal.text = it
        }
        carrinhoViewModel.frete.observe(viewLifecycleOwner) {
            binding.tvFrete.text = it
        }
        carrinhoViewModel.total.observe(viewLifecycleOwner) {
            binding.tvTotal.text = it
        }

        binding.btnVoltar.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnContinuar.setOnClickListener {
            findNavController().navigate(R.id.pagamentoFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        // Chama fetchCart() na MESMA instância
        carrinhoViewModel.fetchCart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
