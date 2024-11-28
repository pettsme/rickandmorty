package com.pettsme.showcase.characters.presentation

import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characters.domain.CharactersRepository
import com.pettsme.showcase.characters.domain.model.CharacterPreview
import com.pettsme.showcase.characters.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characters.presentation.model.CharacterListViewAction.PullRefreshInitiated
import com.pettsme.showcase.characters.presentation.model.CharacterListViewState
import com.pettsme.showcase.characters.presentation.model.CharactersUiModel
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
internal class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository,
    private val uiMapper: CharactersUiMapper,
    private val stringProvider: StringProvider,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterListViewState, CharacterListViewAction>(
    CharacterListViewState.initialState,
    dispatcherProvider,
) {

    private val characterPreviews = mutableListOf<CharacterPreview>()
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
                    characterPreviews.addAll(characterListDomainModel.characterPreviews)
                    nextPage = characterListDomainModel.nextPage
                    refreshStateWithData()
                },
                failure = ::handleError,
            )
        }
    }

    private fun refreshStateWithData() {
        val dataItems: MutableList<CharactersUiModel> = characterPreviews.map { character ->
            uiMapper.map(character)
        }.toMutableList()

        if (nextPage != null) {
            dataItems.add(CharactersUiModel.LoadingUiModel)
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
