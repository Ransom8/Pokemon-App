package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    @SerializedName("version")
    val version: Version
)