package rr.pockemonchallange.ui.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rr.pockemonchallange.R
import rr.pockemonchallange.data.model.PokemonSimpleDto

class PokemonListAdapter(val handler: PokemonListAdapter.ClickHandler) : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    var pokemonList: List<PokemonSimpleDto> = ArrayList(0)

    fun updateList(newList: List<PokemonSimpleDto>)
    {
        pokemonList=newList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pokemons_list_row_item, parent, false)

        return PokemonListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.pokemonName.text=pokemonList.get(position*2).name
        holder.pokemonName.setOnClickListener(View.OnClickListener { v -> handler.onClick(pokemonList.get(position*2)) })
        if (position*2+1<=pokemonList.size) {
            holder.pokemonName2.text = pokemonList.get(position*2+1).name
            holder.pokemonName2.setOnClickListener(View.OnClickListener { v -> handler.onClick(pokemonList.get(position*2+1)) })
        }
    }

    override fun getItemCount(): Int {
        return (pokemonList.size/2)+(pokemonList.size%2)
    }

    class PokemonListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonName: TextView
        val pokemonName2: TextView

        init {
            pokemonName = itemView.findViewById(R.id.pokemonName)
            pokemonName2 = itemView.findViewById(R.id.pokemonName2)
        }
    }

    interface ClickHandler
    {
        fun onClick(pokemon: PokemonSimpleDto)
    }
}