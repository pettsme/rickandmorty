package com.pettsme.showcase.characterlist.presentation.model

import com.pettsme.showcase.characterlist.domain.model.VitalStatus

internal sealed interface CharacterListViewItem {
    data class DataItem(
        val name: String,
        val status: VitalStatus, // domain object in presentation object
    ) : CharacterListViewItem

    data object Loading : CharacterListViewItem
}

internal val fakeCharacterListViewItemList = listOf(
    CharacterListViewItem.DataItem(
        name = "Rick",
        status = VitalStatus.ALIVE,
    ),
    CharacterListViewItem.DataItem(
        name = "Morty",
        status = VitalStatus.DEAD,
    ),
    CharacterListViewItem.DataItem(
        name = "Kevin",
        status = VitalStatus.UNKNOWN,
    ),
)
