package com.olamachia.Pokemon_week_8

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.olamachia.Pokemon_week_8.constant.NetWorkConnection
import com.olamachia.Pokemon_week_8.models.AllPokemon
import com.olamachia.Pokemon_week_8.ViewModel.PokemonViewModel
import com.olamachia.Pokemon_week_8.retrofit.ServiceGenerator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.pokemon_display.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Fragment class handling calls of pokemon views to view model
 */

class DisplayPokemonFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel
    val baseUrl = "https://pokeres.bastionbot.org/images/pokemon"

    var isFragmentVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * Handling network availability
         */

        val networkConnection = NetWorkConnection(activity?.applicationContext!!)
        networkConnection.observe(requireActivity(), Observer {
            if (it) {
                if (isFragmentVisible) {
                    Log.i("Api Called", "Result Api Called")
                    pokemon_list.visibility = View.VISIBLE
                    network_error_handling.visibility = View.GONE
                    viewModel.getPokemonItem()
                }

            } else {
                if (isFragmentVisible) {
                    pokemon_list.visibility = View.GONE
                    network_error_handling.visibility = View.VISIBLE
                }
            }
        })

        return inflater.inflate(R.layout.pokemon_display, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel.getPokemonItemProperties()
        val pokemonID = this.arguments?.getString("POKEMON_ID")
        val pokemonName = this.arguments?.getString("POKEMON_NAME")


        Name.text = pokemonName

        if (pokemonID != null) {
            getPokemonItemProperties(pokemonID)
        }
    }


    fun getPokemonItemProperties(id: String) {
        ServiceGenerator.getPokemonApi().getPokemon(id).enqueue(
            object : Callback<AllPokemon> {
                override fun onResponse(call: Call<AllPokemon>, response: Response<AllPokemon>) {

                    Weight.text = response.body()?.weight.toString()
                    Order.text = response.body()?.order.toString()
                    height.text = response.body()?.height.toString()
                    Exp.text = response.body()?.baseExperience.toString()



                    Picasso.get()
                        .load("$baseUrl/$id.png")
                        .into(PokemonDisplay)

                }

                override fun onFailure(call: Call<AllPokemon>, t: Throwable) {
                    card_view.visibility = View.GONE
                    PokemonDisplay.visibility = View.GONE

                    network_error_handling1.visibility = View.VISIBLE
                }

            }
        )
    }

    /**
     * This function enables the previous fragment to be inflated even when network connection is
     * lost.
     */

    override fun onDestroyView() {
        super.onDestroyView()
        isFragmentVisible = false
    }
}