package com.pettsme.showcase.characterdetails.presentation.model

import com.pettsme.showcase.base.extensions.firstSubtype
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.NameUiModel
import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import com.pettsme.showcase.viewmodelbase.presentation.model.ViewState

internal data class CharacterDetailsState(
    override val isLoading: Boolean,
    override val errorState: ErrorState?,
    val title: String,
    val uiModels: List<CharacterDetailsUiModel>,
) : ViewState {
    val isError = errorState != null

    companion object {
        val initialState =
            CharacterDetailsState(
                true,
                title = "",
                errorState = null,
                uiModels = emptyList(),
            )

        val fakeState =
            CharacterDetailsState(
                isLoading = false,
                errorState = null,
                title = fakeCharacterDetailUiModels
                    .firstSubtype<CharacterDetailsUiModel, NameUiModel>().name,
                uiModels = fakeCharacterDetailUiModels,
            )
    }
}
