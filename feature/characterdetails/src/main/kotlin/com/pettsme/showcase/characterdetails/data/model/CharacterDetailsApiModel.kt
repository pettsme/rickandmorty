package com.pettsme.showcase.characterdetails.data.model

import com.pettsme.showcase.network.data.model.LocationApiModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CharacterDetailsApiModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: LocationApiModel,
    val location: LocationApiModel,
    val image: String,
    val episode: List<String>,
)
