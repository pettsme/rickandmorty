package com.pettsme.showcase.characters.presentation.model

internal sealed interface CharactersUiModel {
    data class CharacterPreviewUiModel(
        val id: Int,
        val name: String,
        val description: String,
        val imageUrl: String,
    ) : CharactersUiModel

    data object LoadingUiModel : CharactersUiModel
}

internal val fakeCharacterUiModels = listOf(
    CharactersUiModel.CharacterPreviewUiModel(
        id = 1,
        name = "Rick",
        description = "Some believe this should be a description..",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    ),
    CharactersUiModel.CharacterPreviewUiModel(
        id = 2,
        name = "Morty",
        description = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
    ),
    CharactersUiModel.CharacterPreviewUiModel(
        id = 3,
        name = "Kevin",
        description = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/191.jpeg",
    ),
)
