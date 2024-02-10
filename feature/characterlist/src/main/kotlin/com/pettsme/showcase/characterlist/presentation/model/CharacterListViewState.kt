package com.pettsme.showcase.characterlist.presentation.model

import com.pettsme.showcase.base.presentation.model.ErrorState
import com.pettsme.showcase.base.presentation.model.LoadingState
import com.pettsme.showcase.base.presentation.model.ViewState

data class CharacterListViewState(
    override val loadingState: LoadingState,
    override val errorState: ErrorState?,
    val data: List<CharacterListViewItem>,
) : ViewState {
    companion object {
        val initialState =
            CharacterListViewState(LoadingState.WholeScreen, errorState = null, data = emptyList())
    }
}
