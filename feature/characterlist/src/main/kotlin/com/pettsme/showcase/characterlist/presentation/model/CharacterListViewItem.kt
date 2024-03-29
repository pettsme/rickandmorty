package com.pettsme.showcase.characterlist.presentation.model

import com.pettsme.showcase.base.domain.model.VitalStatus

internal sealed interface CharacterListViewItem {
    data class DataItem(
        val id: Int,
        val name: String,
        val status: VitalStatus, // domain object in presentation object
        val species: String,
        val imageUrl: String,
    ) : CharacterListViewItem

    data object Loading : CharacterListViewItem
}

internal val fakeCharacterListViewItemList = listOf(
    CharacterListViewItem.DataItem(
        id = 1,
        name = "Rick",
        status = VitalStatus.ALIVE,
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    ),
    CharacterListViewItem.DataItem(
        id = 2,
        name = "Morty",
        status = VitalStatus.DEAD,
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
    ),
    CharacterListViewItem.DataItem(
        id = 3,
        name = "Kevin",
        status = VitalStatus.UNKNOWN,
        species = "Human",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/191.jpeg",
    ),
)
