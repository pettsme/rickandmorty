package com.pettsme.showcase.characterlist.presentation

import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.model.ErrorState
import com.pettsme.showcase.base.presentation.model.LoadingState
import com.pettsme.showcase.characterlist.domain.CharacterListRepository
import com.pettsme.showcase.characterlist.domain.model.CharacterListItemDomainModel
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewItem
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewState
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CharacterListViewModel @Inject constructor(
    private val repository: CharacterListRepository,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterListViewState, CharacterListViewAction>(
    CharacterListViewState.initialState,
    dispatcherProvider,
) {

    private val characters = mutableListOf<CharacterListItemDomainModel>()
    private var nextPage = 0

    init {
        launch {
            updateState { state -> state.copy(loadingState = LoadingState.WholeScreen) }
            repository.getCharacters().process(
                success = { characterListDomainModel ->
                    characters.addAll(characterListDomainModel.characters)
                    nextPage = characterListDomainModel.nextPage

                    refreshStateWithData()
                },
                failure = ::handleError,
            )
        }
    }

    private fun refreshStateWithData() {
        updateState { state ->
            state.copy(
                loadingState = LoadingState.NotLoading,
                data = characters.map {
                    CharacterListViewItem(
                        it.name,
                    )
                },
            )
        }
    }

    override fun onViewAction(viewAction: CharacterListViewAction) {
        // TODO "Not yet implemented"
    }

    override fun handleError(throwable: Throwable) {
        updateState { state ->
            state.copy(
                loadingState = LoadingState.NotLoading,
                errorState = ErrorState.InlineError(throwable.message ?: "Ops"),
            )
        }
    }
}
