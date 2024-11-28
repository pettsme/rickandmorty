package com.pettsme.showcase.characters.presentation.model

internal sealed interface CharacterListViewAction {
    data object PullRefreshInitiated : CharacterListViewAction
    data object LoadNextPage : CharacterListViewAction
}
