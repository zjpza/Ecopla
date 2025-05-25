package com.example.projetonovoecopla.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.data.network.ResultWrapper
import com.example.projetonovoecopla.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: ProdutoAdapter
    private lateinit var carrinhoViewModel: CarrinhoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        carrinhoViewModel = ViewModelProvider(requireActivity())[CarrinhoViewModel::class.java]

        // Configura o adapter com lista vazia no começo
        adapter = ProdutoAdapter(emptyList()) { produto ->
            // Ao clicar no produto, adiciona ao carrinho e navega
            carrinhoViewModel.addItemToCart(produto, 1)
            findNavController().navigate(R.id.carrinhoFragment)
        }

        // Configura RecyclerView
        binding.recyclerViewProdutos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewProdutos.adapter = adapter

        // Botões filtro e carrinho
        binding.btnFiltro.setOnClickListener {
            findNavController().navigate(R.id.filtroBottomSheetFragment)
        }
        binding.btnCarrinho.setOnClickListener {
            findNavController().navigate(R.id.carrinhoFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa lista de produtos
        homeViewModel.produtos.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    adapter.submitList(result.value)
                }
                is ResultWrapper.GenericError -> {
                    Toast.makeText(requireContext(), "Erro ao carregar produtos.", Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.NetworkError -> {
                    Toast.makeText(requireContext(), "Erro de conexão.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        homeViewModel.fetchProdutos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
