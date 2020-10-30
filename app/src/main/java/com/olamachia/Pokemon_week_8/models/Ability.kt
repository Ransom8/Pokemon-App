package com.olamachia.Pokemon_week_8.models

import com.google.gson.annotations.SerializedName

/**
 * Here, the class for the pokemon Ability which we will be getting from the API
 */

data class Ability(
    @SerializedName("ability")
    val ability: AbilityX,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)