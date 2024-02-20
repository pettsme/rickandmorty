package com.pettsme.showcase.characterdetails.presentation.model

import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import com.pettsme.showcase.viewmodelbase.presentation.model.ViewState

internal data class CharacterDetailsViewState(
    override val isLoading: Boolean,
    override val errorState: ErrorState?,
    val data: CharacterDetailsViewData?,
) : ViewState {
    val isError = errorState != null

    companion object {
        val initialState =
            CharacterDetailsViewState(
                true,
                errorState = null,
                data = null,
            )

        val fakeState =
            CharacterDetailsViewState(
                isLoading = false,
                errorState = null,
                data = fakeCharacterDetailsViewData,
            )
    }
}
