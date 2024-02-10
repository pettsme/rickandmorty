package com.pettsme.showcase.characterlist.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListItemApiModel(
    val name: String,
)
