package com.pettsme.showcase.characterlist.presentation.model

import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import com.pettsme.showcase.viewmodelbase.presentation.model.ViewState

internal data class CharacterListViewState(
    override val isLoading: Boolean,
    override val errorState: ErrorState?,
    val data: List<CharacterListViewItem>,
) : ViewState {
    val isError = errorState != null

    companion object {
        val initialState =
            CharacterListViewState(
                true,
                errorState = null,
                data = emptyList(),
            )

        val fakeState =
            CharacterListViewState(
                isLoading = false,
                errorState = null,
                data = fakeCharacterListViewItemList,
            )
    }
}
