package com.pettsme.showcase.characters.presentation

import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characters.domain.CharactersRepository
import com.pettsme.showcase.characters.domain.model.CharacterPreview
import com.pettsme.showcase.characters.presentation.model.CharactersAction
import com.pettsme.showcase.characters.presentation.model.CharactersAction.PullRefreshInitiated
import com.pettsme.showcase.characters.presentation.model.CharactersState
import com.pettsme.showcase.characters.presentation.model.CharactersUiModel
import com.pettsme.showcase.core.domain.model.base.RepositoryError
import com.pettsme.showcase.core.domain.model.base.onError
import com.pettsme.showcase.core.domain.model.base.onSuccess
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
) : BaseViewModel<CharactersState, CharactersAction>(
    CharactersState.initialState,
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
            delay(1000) // just to see the loading -,-
            repository.getCharacters(page = page)
                .onSuccess { characterListDomainModel ->
                    characterPreviews.addAll(characterListDomainModel.characterPreviews)
                    nextPage = characterListDomainModel.nextPage
                    refreshStateWithData()
                }
                .onError(::handleError)
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

    override fun onViewAction(viewAction: CharactersAction) {
        when (viewAction) {
            PullRefreshInitiated -> {
                getData(page = 1)
            }

            CharactersAction.LoadNextPage -> nextPage?.let { getData(it) }
        }
    }

    override fun handleError(throwable: RepositoryError) {
        updateState { state ->
            state.copy(
                isLoading = false,
                errorState = ErrorState.InlineError(
                    throwable.description
                        ?: stringProvider.getString(R.string.error_message_unknown_error),
                ),
            )
        }
    }
}
