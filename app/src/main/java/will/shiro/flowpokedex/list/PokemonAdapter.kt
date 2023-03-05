package will.shiro.flowpokedex.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import will.shiro.flowpokedex.R
import will.shiro.flowpokedex.databinding.ItemListPokemonBinding
import will.shiro.flowpokedex.domain.entity.Pokemon

class PokemonAdapter constructor(
    private val onClick: (String) -> Unit
) : PagingDataAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(
    PokemonDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            binding = ItemListPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let(holder::setUp)
    }

    inner class PokemonViewHolder(
        private val binding: ItemListPokemonBinding,
        private val onClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setUp(apiPokemon: Pokemon) {
            binding.pokemonNameText.text = binding.root.context.getString(
                R.string.list_item,
                apiPokemon.name,
                apiPokemon.id
            )
            binding.root.setOnClickListener {
                onClick(apiPokemon.id)
            }
        }
    }
}

class PokemonDiffUtil : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }
}