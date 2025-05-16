package com.example.projetonovoecopla.ui.Encomenda

import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.databinding.FragmentEncomendaBinding

class EncomendaFragment : Fragment() {

    private var _binding: FragmentEncomendaBinding? = null
    private val binding get() = _binding!!

    private val getDocument = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val documentName = getFileName(uri)

            Toast.makeText(requireContext(), "Documento selecionado: $documentName", Toast.LENGTH_SHORT).show()
            binding.textEncomenda.text = documentName
        }
    }

    private fun getFileName(uri: Uri): String {
        var fileName = ""
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (it.moveToFirst()) {
                fileName = it.getString(nameIndex)
            }
        }
        return fileName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val encomendaViewModel =
            ViewModelProvider(this).get(EncomendaViewModel::class.java)

        _binding = FragmentEncomendaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textEncomenda
        encomendaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btnAdicionar.setOnClickListener {
            showFilePicker()
        }

        binding.btnEnviar.setOnClickListener {
            showSendDialog()
        }

        return root
    }

    private fun showFilePicker() {
        getDocument.launch("application/*")
    }

    private fun showSendDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_enviar, null)
        val dialogTextView = dialogView.findViewById<TextView>(R.id.textDialog)

        val dialogViewModel = ViewModelProvider(this)[DialogViewModel::class.java]

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
