package com.pettsme.showcase.characterdetails.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullLocationApiModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)
