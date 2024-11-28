package com.pettsme.showcase.characters.data.model

import com.pettsme.showcase.network.data.model.LocationApiModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CharacterPreviewApiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: LocationApiModel,
    val gender: String,
)
