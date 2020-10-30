package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

data class PokemonItem(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: ArrayList<Results?>

)