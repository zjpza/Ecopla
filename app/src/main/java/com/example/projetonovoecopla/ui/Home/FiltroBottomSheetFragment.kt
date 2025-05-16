package com.example.projetonovoecopla.ui.Home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.databinding.FragmentFiltroBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.slider.Slider

class FiltroBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFiltroBottomSheetBinding

    private var corSelecionada: Int? = null
    private var materialSelecionado: String? = null
    private var precoSelecionado: Int = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiltroBottomSheetBinding.inflate(inflater, container, false)

        setupSeletores()
        setupSlider()
        setupBotoes()

        return binding.root
    }

    private fun setupSeletores() {
        val cores = listOf(
            binding.corVermelho to Color.RED,
            binding.corAmarelo to Color.YELLOW,
            binding.corVerde to Color.GREEN,
            binding.corAzul to Color.BLUE,
            binding.corRoxo to Color.MAGENTA,
            binding.corLaranja to Color.rgb(255, 165, 0),
            binding.corPreto to Color.BLACK,
            binding.corBranco to Color.WHITE
        )

        cores.forEach { (view, color) ->
            view.setOnClickListener {
                cores.forEach {
                    val drawable = it.first.background.mutate() as GradientDrawable
                    drawable.setColor(it.second)
                    it.first.background = drawable
                    it.first.alpha = 1f
                }
                val drawableSelecionado = view.background.mutate() as GradientDrawable
                drawableSelecionado.setColor(color)
                view.background = drawableSelecionado
                view.alpha = 0.5f
                corSelecionada = color
            }
        }

        val materiais = listOf(
            binding.materialPs to "PS",
            binding.materialAbs to "ABS",
            binding.materialPe to "PE"
        )

        materiais.forEach { (view, material) ->
            view.setOnClickListener {
                materiais.forEach { it.first.alpha = 1f }
                view.alpha = 0.5f
                materialSelecionado = material
            }
        }
    }

    private fun setupSlider() {
        binding.sliderPreco.addOnChangeListener { slider, value, _ ->
            precoSelecionado = value.toInt()
            binding.tvPrecoValor.text = buildString {
        append("Até R$ ")
        append(precoSelecionado)
    }
        }
    }

    private fun setupBotoes() {
        binding.btnAplicar.setOnClickListener {
            findNavController().navigate(com.example.projetonovoecopla.R.id.navigation_home)
            dismiss()
        }

        binding.btnVoltar.setOnClickListener {
            findNavController().navigate(com.example.projetonovoecopla.R.id.navigation_home)
            dismiss()
        }
    }
}
