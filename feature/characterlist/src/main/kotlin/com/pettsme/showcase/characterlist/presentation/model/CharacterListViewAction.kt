package com.pettsme.showcase.characterlist.presentation.model

internal sealed interface CharacterListViewAction {
    data object PullRefreshInitiated : CharacterListViewAction
    data object LoadNextPage : CharacterListViewAction
}
