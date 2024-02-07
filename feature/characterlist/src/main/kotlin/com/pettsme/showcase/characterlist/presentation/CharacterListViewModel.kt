package com.pettsme.showcase.characterlist.presentation

import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.model.LoadingState
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewState
import com.pettsme.showcase.characterlist.presentation.model.CharacterViewItem
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterListViewState, CharacterListViewAction>(
    CharacterListViewState.initialState,
    dispatcherProvider,
) {

    init {
        updateState { state ->
            state.copy(
                loadingState = LoadingState.NotLoading,
                data = listOf(
                    CharacterViewItem("Char 1"),
                    CharacterViewItem("Char 2"),
                    CharacterViewItem("Char 3"),
                    CharacterViewItem("Char 4"),
                ),
            )
        }
    }
    override fun onViewAction(viewAction: CharacterListViewAction) {
        // TODO "Not yet implemented"
    }
    override fun handleError(throwable: Throwable) {
        // TODO "Not yet implemented"
    }
}
