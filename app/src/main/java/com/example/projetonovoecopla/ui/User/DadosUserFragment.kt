package com.example.projetonovoecopla.ui.User

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentDadosBinding

class DadosUserFragment : Fragment() {

    private var _binding: FragmentDadosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDadosBinding.inflate(inflater, container, false)

        binding.botaoVoltarUser.setOnClickListener {
            findNavController().navigate(R.id.navigation_user)
        }

        return binding.root
    }

}