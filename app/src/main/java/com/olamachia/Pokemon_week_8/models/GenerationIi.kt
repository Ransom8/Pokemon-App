package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal")
    val crystal: Crystal,
    @SerializedName("gold")
    val gold: Gold,
    @SerializedName("silver")
    val silver: Silver
)