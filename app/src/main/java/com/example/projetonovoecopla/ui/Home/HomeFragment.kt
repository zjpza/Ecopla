package com.example.projetonovoecopla.ui.Home

import ProdutoAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var produtoAdapter: ProdutoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewModel
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // Adapter e RecyclerView
        produtoAdapter = ProdutoAdapter()
        binding.recyclerViewProdutos.adapter = produtoAdapter

        // Observers
        homeViewModel.produtos.observe(viewLifecycleOwner) { lista ->
            produtoAdapter.submitList(lista)
        }

        homeViewModel.error.observe(viewLifecycleOwner) { mensagemErro ->
            mensagemErro?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnFiltro.setOnClickListener {
            findNavController().navigate(R.id.filtroBottomSheetFragment)
        }

        binding.btnCarrinho.setOnClickListener {
            findNavController().navigate(R.id.carrinhoFragment)
        }

        homeViewModel.carregarProdutos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
