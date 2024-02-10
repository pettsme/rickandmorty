package com.pettsme.showcase.characterlist.data.model

import com.pettsme.showcase.network.data.model.PageMetaData
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListApiModel(
    val info: PageMetaData,
    val results: List<CharacterListItemApiModel>,
)
