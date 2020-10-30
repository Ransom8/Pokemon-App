package com.olamachia.Pokemon_week_8


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.olamachia.Pokemon_week_8.ViewModel.PokemonViewModel


class PokemonListActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, PokemonListFragment())
            addToBackStack(null)
            commit()
        }

    }

}

