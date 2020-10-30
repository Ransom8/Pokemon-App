package com.olamachia.Pokemon_week_8.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olamachia.Pokemon_week_8.models.PokemonItem
import com.olamachia.Pokemon_week_8.models.Results
import com.olamachia.Pokemon_week_8.retrofit.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * View model class for pokemon using Livedata
 */

class PokemonViewModel : ViewModel() {

    /**
     * Declare global variables
     */

    private val _result = MutableLiveData<MutableList<Results?>>()
    val pokemonList: LiveData<MutableList<Results?>>
        get() = _result

    var next: String? = null

    var failure = MutableLiveData<Boolean>()

    /**
     * Get pokemon views from API and handle network state
     */

    fun getPokemonItem() {
        ServiceGenerator.getPokemonApi().getPokemonItem().enqueue(
            object : Callback<PokemonItem> {
                override fun onResponse(
                    call: Call<PokemonItem>,
                    response: Response<PokemonItem>
                ) {
//                    Log.d("Overview", "${response.body()}")
                    _result.value = response.body()?.results

                }

                override fun onFailure(call: Call<PokemonItem>, t: Throwable) {
                    failure.value = true
                }
            }
        )
    }

}