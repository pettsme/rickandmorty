package com.pettsme.showcase.characterlist.presentation

import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characterlist.domain.CharacterListRepository
import com.pettsme.showcase.characterlist.domain.model.CharacterListItemDomainModel
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction.PullRefreshInitiated
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewItem
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewState
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
internal class CharacterListViewModel @Inject constructor(
    private val repository: CharacterListRepository,
    private val stringProvider: StringProvider,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterListViewState, CharacterListViewAction>(
    CharacterListViewState.initialState,
    dispatcherProvider,
) {

    private val characters = mutableListOf<CharacterListItemDomainModel>()
    private var nextPage: Int? = null

    init {
        getData(page = 1)
    }

    private fun getData(page: Int) {
        launch {
            updateState { state ->
                state.copy(
                    isLoading = nextPage == null,
                )
            }
            delay(2000)
            repository.getCharacters(page = page).process(
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
        val dataItems: MutableList<CharacterListViewItem> = characters.map {
            CharacterListViewItem.DataItem(
                id = it.id,
                name = it.name,
                status = it.vitalStatus,
                species = it.species,
                imageUrl = it.imageUrl,
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
                getData(page = 1)
            }

            CharacterListViewAction.LoadNextPage -> nextPage?.let { getData(it) }
        }
    }

    override fun handleError(throwable: Throwable) {
        updateState { state ->
            state.copy(
                isLoading = false,
                errorState = ErrorState.InlineError(
                    throwable.message
                        ?: stringProvider.getString(R.string.error_message_unknown_error),
                ),
            )
        }
    }
}
