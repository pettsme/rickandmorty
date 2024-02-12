package com.pettsme.showcase.characterdetails.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CharacterDetailsApiModel(
    val id: Int,
    val name: String,
)
