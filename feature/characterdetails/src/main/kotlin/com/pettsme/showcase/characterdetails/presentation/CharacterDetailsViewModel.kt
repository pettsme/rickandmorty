package com.pettsme.showcase.characterdetails.presentation

import androidx.lifecycle.SavedStateHandle
import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepository
import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.domain.model.FullLocation
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction.LocationExpanded
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsState
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel
import com.pettsme.showcase.core.domain.model.base.RepositoryError
import com.pettsme.showcase.core.domain.model.base.onError
import com.pettsme.showcase.core.domain.model.base.onSuccess
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CharacterDetailsViewModel @Inject constructor(
    private val repository: CharacterDetailsRepository,
    private val uiMapper: CharacterDetailsUiMapper,
    private val stringProvider: StringProvider,
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterDetailsState, CharacterDetailsAction>(
    CharacterDetailsState.initialState,
    dispatcherProvider,
) {
    private val characterId: Int = checkNotNull(savedStateHandle["id"])

    init {
        getData()
    }

    private fun getData() {
        launch {
            updateState { state ->
                state.copy(
                    isLoading = true,
                )
            }
            repository.getCharacter(characterId)
                .onSuccess(::onCharacterLoaded)
                .onError(::handleError)
        }
    }

    private fun onCharacterLoaded(character: Character) {
        updateState { state ->
            state.copy(
                isLoading = false,
                data = uiMapper.map(character),
            )
        }
        getEpisodesForCharacter(character)
    }

    private fun getEpisodesForCharacter(character: Character) {
        launch {
            repository.getEpisodesForCharacter(character.presentInEpisodesIds)
                .onSuccess { episodes -> onEpisodesLoaded(character, episodes) }
                .onError(::handleError)
        }
    }

    private fun onEpisodesLoaded(character: Character, episodeList: List<Episode>) {
        updateState { state ->
            state.copy(
                data = uiMapper.map(character, episodeList),
            )
        }
    }

    override fun onViewAction(viewAction: CharacterDetailsAction) {
        when (viewAction) {
            is LocationExpanded -> {
                launch {
                    // this could be pre loaded, but decided to only load if user is curious....
                    // ok not the best UX :)
                    repository.getLocationById(viewAction.locationId)
                        .onSuccess {
                            onLocationLoaded(viewAction, it)
                        }
                        .onError(::handleError)
                }
            }
        }
    }

    private fun onLocationLoaded(
        viewAction: LocationExpanded,
        it: FullLocation,
    ) {
        updateState { state ->
            if (viewAction.type == LocationUiModel.LocationType.ORIGIN) {
                state.copy(
                    data = state.data?.copy(
                        originFullLocation = it,
                    ),
                )
            } else {
                state.copy(
                    data = state.data?.copy(
                        lastKnownFullLocation = it,
                    ),
                )
            }
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
