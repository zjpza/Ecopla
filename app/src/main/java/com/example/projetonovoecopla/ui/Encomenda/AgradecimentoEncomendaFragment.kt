package com.example.projetonovoecopla.ui.Encomenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentAgradecimentoEncomendaBinding

class AgradecimentoEncomendaFragment : Fragment() {

    private var _binding: FragmentAgradecimentoEncomendaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgradecimentoEncomendaBinding.inflate(inflater, container, false)

        binding.botaoVoltarEncomenda.setOnClickListener {
            findNavController().navigate(R.id.navigation_encomenda)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()
    }
}