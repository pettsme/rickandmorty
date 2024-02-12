package com.pettsme.showcase.characterdetails.presentation.model

internal data class CharacterDetailsViewData(
    val name: String,
)

internal val fakeCharacterDetailsViewData = CharacterDetailsViewData(
    name = "Rick",
)
