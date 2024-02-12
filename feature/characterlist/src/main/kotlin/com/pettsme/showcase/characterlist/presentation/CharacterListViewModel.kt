package com.pettsme.showcase.characterlist.presentation

import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.model.ErrorState
import com.pettsme.showcase.characterlist.domain.CharacterListRepository
import com.pettsme.showcase.characterlist.domain.model.CharacterListItemDomainModel
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction.PullRefreshInitiated
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
    private var nextPage: Int? = null

    init {
        println("init")
        getData(page = 1)
    }

    private fun getData(page: Int) {
        launch {
            updateState { state ->
                state.copy(
                    isLoading = nextPage == 1,
                )
            }
            repository.getCharacters(page = page).process(
                success = { characterListDomainModel ->
                    println("get chars, page: $page")
                    characters.addAll(characterListDomainModel.characters)
                    nextPage = characterListDomainModel.nextPage

                    println("nextPage: $nextPage")
                    refreshStateWithData()
                },
                failure = ::handleError,
            )
        }
    }

    private fun refreshStateWithData() {
        val dataItems: MutableList<CharacterListViewItem> = characters.map {
            CharacterListViewItem.DataItem(
                it.name,
                it.vitalStatus,
            )
        }.toMutableList()

        if (nextPage != null) {
            dataItems.add(CharacterListViewItem.Loading)
        }

        updateState { state ->
            state.copy(
                isLoading = false,
                data = dataItems,
            )
        }
    }

    override fun onViewAction(viewAction: CharacterListViewAction) {
        when (viewAction) {
            PullRefreshInitiated -> {
                println("pull refresh init")
                getData(page = 1)
            }

            CharacterListViewAction.LoadNextPage -> nextPage?.let { getData(it) }
        }
    }

    override fun handleError(throwable: Throwable) {
        println("error")
        updateState { state ->
            state.copy(
                isLoading = false,
                errorState = ErrorState.InlineError(throwable.message ?: "Ops"),
            )
        }
    }
}
