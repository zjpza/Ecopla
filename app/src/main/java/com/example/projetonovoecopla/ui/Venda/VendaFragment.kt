package com.example.projetonovoecopla.ui.Venda

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
import com.example.projetonovoecopla.databinding.FragmentVendaBinding

class VendaFragment : Fragment() {

    private var _binding: FragmentVendaBinding? = null
    private val binding get() = _binding!!

    private val getDocument = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val documentName = getFileName(uri)

            Toast.makeText(requireContext(), "Documento selecionado: $documentName", Toast.LENGTH_SHORT).show()

            val fileNameTextView = TextView(requireContext()).apply {
                text = documentName
                textSize = 16f
                setPadding(0, 8, 0, 8)
            }
            binding.layoutListaArquivos.addView(fileNameTextView)
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
        val vendaViewModel =
            ViewModelProvider(this).get(VendaViewModel::class.java)

        val dialogViewModelVenda =
            ViewModelProvider(this).get(DialogViewModelVenda::class.java)

        _binding = FragmentVendaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textVenda
        vendaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btnAdicionar.setOnClickListener {
            showFilePicker()
        }

        binding.btnEnviar.setOnClickListener {
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_enviar, null)
            val dialogTextView = dialogView.findViewById<TextView>(R.id.textDialog)

            dialogViewModelVenda.text.observe(viewLifecycleOwner) { texto ->
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
                findNavController().navigate(R.id.agradecimentoVendaFragment)
                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
        }

        return root
    }

    private fun showFilePicker() {
        getDocument.launch("application/*")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}