package com.olamachia.Pokemon_week_8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.Pokemon_week_8.models.Results
import com.olamachia.Pokemon_week_8.constant.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_item.view.*

/**
 * Adapter for pokemon recycler view
 */

class PokemonAdapter(var pokemonList: MutableList<Results?>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    val baseUrl = "https://pokeres.bastionbot.org/images/pokemon"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent,
            false)
        return PokemonViewHolder(view)

    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        if (holder.itemViewType == Constants.VIEW_TYPE_ITEM) {
            val currentPokemon = pokemonList[position]
            val pokemonImageView = holder.itemView.ivPokemonImage
            val pokemonName = holder.itemView.pokemonName

            pokemonName.text = currentPokemon?.name

            val url = currentPokemon?.url?.split("/")
            val id = url?.get(url.size - 2)
            Picasso.get()
                .load("$baseUrl/$id.png")
                .into(pokemonImageView)
        }

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setMyList(resultsList: MutableList<Results?>) {
        this.pokemonList = resultsList
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        return if (pokemonList[position] == null) {
            Constants.VIEW_TYPE_LOADING
        } else {
            Constants.VIEW_TYPE_ITEM
        }
    }


    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}

