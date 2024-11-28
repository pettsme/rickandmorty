package com.pettsme.showcase.characters.presentation.model

import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import com.pettsme.showcase.viewmodelbase.presentation.model.ViewState

internal data class CharactersState(
    override val isLoading: Boolean,
    override val errorState: ErrorState?,
    val data: List<CharactersUiModel>,
) : ViewState {
    val isError = errorState != null

    companion object {
        val initialState =
            CharactersState(
                true,
                errorState = null,
                data = emptyList(),
            )

        val fakeState =
            CharactersState(
                isLoading = false,
                errorState = null,
                data = fakeCharacterUiModels,
            )
    }
}
