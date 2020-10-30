package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("name")
    var name: String,
    @SerializedName("url")
    var url: String
)

