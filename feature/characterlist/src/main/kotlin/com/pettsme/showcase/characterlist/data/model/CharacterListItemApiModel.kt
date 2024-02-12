package com.pettsme.showcase.characterlist.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CharacterListItemApiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: LocationApiModel,
)

@JsonClass(generateAdapter = true)
internal data class LocationApiModel(val name: String, val url: String)
