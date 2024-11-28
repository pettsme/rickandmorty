package com.pettsme.showcase.characters.data.model

import com.pettsme.showcase.network.data.model.PageMetaData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CharactersApiModel(
    @Json(name = "info")
    val metaData: PageMetaData,
    @Json(name = "results")
    val characters: List<CharacterPreviewApiModel>,
)
