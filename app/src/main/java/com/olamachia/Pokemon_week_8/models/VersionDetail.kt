package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int,
    @SerializedName("version")
    val version: VersionX
)