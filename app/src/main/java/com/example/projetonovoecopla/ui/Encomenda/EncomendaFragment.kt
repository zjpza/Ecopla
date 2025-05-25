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

    private val arquivosSelecionados = mutableListOf<String>()

    private val getDocument = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val documentName = getFileName(uri)

            // Adiciona o nome do arquivo à lista
            arquivosSelecionados.add(documentName)

            // Atualiza a lista exibida
            atualizarListaArquivos()
        }
    }

    private fun atualizarListaArquivos() {
        binding.layoutListaArquivos.removeAllViews()

        for (arquivo in arquivosSelecionados) {
            val textView = TextView(requireContext())
            textView.text = arquivo
            textView.textSize = 16f
            textView.setPadding(0, 4, 0, 4)

            binding.layoutListaArquivos.addView(textView)
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
        val encomendaViewModel = ViewModelProvider(this).get(EncomendaViewModel::class.java)

        _binding = FragmentEncomendaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Observa o texto fixo do ViewModel, se quiser manter dinâmica
        encomendaViewModel.text.observe(viewLifecycleOwner) {
            binding.textEncomenda.text = it
        }

        binding.btnAdicionar.setOnClickListener {
            getDocument.launch("application/*")
        }

        binding.btnEnviar.setOnClickListener {
            showSendDialog()
        }

        return root
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
