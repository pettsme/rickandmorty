package com.pettsme.showcase.characterlist.presentation.model

import com.pettsme.showcase.characterlist.domain.model.VitalStatus

internal sealed interface CharacterListViewItem {
    data class DataItem(
        val name: String,
        val status: VitalStatus, // domain object in presentation object
        val species: String,
        val imageUrl: String,
    ) : CharacterListViewItem

    data object Loading : CharacterListViewItem
}

internal val fakeCharacterListViewItemList = listOf(
    CharacterListViewItem.DataItem(
        name = "Rick",
        status = VitalStatus.ALIVE,
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    ),
    CharacterListViewItem.DataItem(
        name = "Morty",
        status = VitalStatus.DEAD,
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
    ),
    CharacterListViewItem.DataItem(
        name = "Kevin",
        status = VitalStatus.UNKNOWN,
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/191.jpeg",
    ),
)
