package com.example.projetonovoecopla.ui.Encomenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.R.*
import com.example.projetonovoecopla.R.id.textDialog
import com.example.projetonovoecopla.databinding.FragmentEncomendaBinding


class EncomendaFragment : Fragment() {

    private var _binding: FragmentEncomendaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val encomendaViewModel =
            ViewModelProvider(this).get(EncomendaViewModel::class.java)

        val DialogViewModel =
            ViewModelProvider(this).get(DialogViewModel::class.java)

        _binding = FragmentEncomendaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textEncomenda
        encomendaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btnAdicionar.setOnClickListener {
            Toast.makeText(requireContext(), "Arquivo adicionado", Toast.LENGTH_SHORT).show()
        }

        binding.btnEnviar.setOnClickListener {
            val dialogView = LayoutInflater.from(requireContext()).inflate(layout.dialog_enviar, null)
            val dialogTextView = dialogView.findViewById<TextView>(textDialog)

            val dialogViewModel = ViewModelProvider(this).get(DialogViewModel::class.java)

            dialogViewModel.text.observe(viewLifecycleOwner) { texto ->
                dialogTextView.text = texto
            }

            val dialog = android.app.AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create()

            val btnNao = dialogView.findViewById<Button>(R.id.btnCancelar)
            btnNao.setOnClickListener {
                dialog.dismiss()
            }

            val btnSim = dialogView.findViewById<Button>(R.id.btn_sim)
            btnSim.setOnClickListener {
                findNavController().navigate(R.id.agradecimentoEncomendaFragment)
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}