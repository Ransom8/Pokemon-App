package com.olamachia.Pokemon_week_8.retrofit

import com.olamachia.Pokemon_week_8.models.AllPokemon
import com.olamachia.Pokemon_week_8.models.PokemonItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * This is where the interface that we use to declare the limit of data we want to get from the API
 * and other URL extension we will be using for the retrofit
 */

interface Api {
    @GET("pokemon?limit=40&offset=0/")
    fun getPokemonItem(): Call<PokemonItem>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") key: String): Call<AllPokemon>

    @GET
    fun getMorePokemon(@Url url: String): Call<PokemonItem>
}