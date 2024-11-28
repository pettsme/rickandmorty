package com.pettsme.showcase.characters.presentation.model

internal sealed interface CharactersAction {
    data object PullRefreshInitiated : CharactersAction
    data object LoadNextPage : CharactersAction
}
