package com.example.projetonovoecopla.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.data.models.Produto

class ProdutoAdapter(
    private val produtos: List<Produto>,
    private val onItemClick: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {


    private var listaProdutos: List<Produto> = listOf()

    fun submitList(novaLista: List<Produto>) {
        listaProdutos = novaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.bind(produto)
    }

    override fun getItemCount(): Int = listaProdutos.size

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nome = itemView.findViewById<TextView>(R.id.textNome)
        private val preco = itemView.findViewById<TextView>(R.id.textPreco)

        fun bind(produto: Produto) {
            nome.text = produto.nome
            preco.text = "R$ %.2f".format(produto.preco)
        }
    }
}
