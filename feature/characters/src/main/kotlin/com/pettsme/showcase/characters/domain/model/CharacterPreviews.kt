package com.pettsme.showcase.characters.domain.model

internal data class CharacterPreviews(
    val nextPage: Int?,
    val characterPreviews: List<CharacterPreview>,
)
