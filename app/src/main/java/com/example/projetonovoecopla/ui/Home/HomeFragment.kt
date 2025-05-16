package com.example.projetonovoecopla.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val btnFiltro: ImageButton = binding.btnFiltro
        val btnCarrinho: ImageButton = binding.btnCarrinho

        btnFiltro.setOnClickListener {
            findNavController().navigate(R.id.filtroBottomSheetFragment)
        }

        btnCarrinho.setOnClickListener {
            findNavController().navigate(R.id.carrinhoFragment)
            }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Evitar memory leak
        _binding = null
    }
}
