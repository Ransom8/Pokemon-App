package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)