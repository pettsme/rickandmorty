package com.pettsme.showcase.characterdetails.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeApiModel(
    val id: Int,
    val name: String,
    val episode: String,
    @Json(name = "air_date") val airDate: String,
)
