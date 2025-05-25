package com.example.projetonovoecopla.ui.Cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetonovoecopla.data.models.CartItem
import com.example.projetonovoecopla.databinding.ItemCartBinding

class CartAdapter(
    private val items: List<CartItem>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNome.text = item.name
        holder.binding.tvQuantidade.text = "Qtd: ${item.quantity}"
        holder.binding.tvPreco.text = "R$ %.2f".format(item.price)

    }

    override fun getItemCount(): Int = items.size
}
