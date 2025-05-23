import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projetonovoecopla.data.models.Produto
import com.example.projetonovoecopla.databinding.ItemProdutoBinding

class ProdutoAdapter : ListAdapter<Produto, ProdutoAdapter.ProdutoViewHolder>(DiffCallback()) {

    inner class ProdutoViewHolder(private val binding: ItemProdutoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(produto: Produto) {
            binding.textNome.text = produto.nome
            binding.textPreco.text = "R$ %.2f".format(produto.preco)
            binding.textMaterial.text = produto.material ?: "Material não informado"
            binding.textCor.text = produto.cor ?: "Cor não informada"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Produto>() {
        override fun areItemsTheSame(oldItem: Produto, newItem: Produto) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Produto, newItem: Produto) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
