package com.example.projetonovoecopla.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetonovoecopla.R
import com.example.projetonovoecopla.data.models.CartItem

class CarrinhoAdapter(
    private var items: List<CartItem>
) : RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    fun submitList(novaLista: List<CartItem>) {
        items = novaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_produto_carrinho, parent, false)
        return CarrinhoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class CarrinhoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nome = itemView.findViewById<TextView>(R.id.textNome)
        private val preco = itemView.findViewById<TextView>(R.id.textPreco)

        fun bind(item: CartItem) {
            nome.text = item.nome
            preco.text = "R$ %.2f".format(item.preco)

        }
    }
}
