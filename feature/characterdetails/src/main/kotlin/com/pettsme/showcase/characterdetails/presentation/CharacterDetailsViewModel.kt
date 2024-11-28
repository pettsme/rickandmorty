package com.pettsme.showcase.characterdetails.presentation

import androidx.lifecycle.SavedStateHandle
import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepository
import com.pettsme.showcase.characterdetails.domain.model.CharacterDetailsDomainModel
import com.pettsme.showcase.characterdetails.domain.model.EpisodeDomainModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsState
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import com.pettsme.showcase.viewmodelbase.presentation.model.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CharacterDetailsViewModel @Inject constructor(
    private val repository: CharacterDetailsRepository,
    private val stringProvider: StringProvider,
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterDetailsState, CharacterDetailsAction>(
    CharacterDetailsState.initialState,
    dispatcherProvider,
) {
    private val characterId: Int = checkNotNull(savedStateHandle["id"])
    private var characterDetails: CharacterDetailsDomainModel? = null

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
            repository.getCharacter(characterId).process(
                success = { model ->
                    characterDetails = model
                    refreshStateWithData()
                    getEpisodesForCharacter()
                },
                failure = ::handleError,
            )
        }
    }

    private fun getEpisodesForCharacter() {
        launch {
            characterDetails?.let {
                repository.getEpisodesForCharacter(it).process(
                    success = {
                        updateState { state ->
                            state.copy(
                                data = state.data?.copy(
                                    presentInEpisodes = it.map { episode ->
                                        with(episode) {
                                            EpisodeUiModel(
                                                id = id,
                                                name = name,
                                                episodeCode = episodeCode,
                                                aired = aired,
                                            )
                                        }
                                    },
                                ),
                            )
                        }
                    },
                    failure = ::handleError,
                )
            }
        }
    }

    private fun refreshStateWithData() {
        characterDetails?.let { details ->
            launch {
                repository.getEpisodesForCharacter(details).process(
                    success = { episodes: List<EpisodeDomainModel> ->
                        with(details) { // this gives context to the context receiver function toViewData()
                            updateState { state ->
                                state.copy(
                                    isLoading = false,
                                    data = episodes.toViewData(),
                                )
                            }
                        }
                    },
                    failure = ::handleError,
                )
            }
        }
    }

    override fun onViewAction(viewAction: CharacterDetailsAction) {
        when (viewAction) {
            is CharacterDetailsAction.LocationExpanded -> {
                launch {
                    repository.getLocationById(viewAction.locationId).process(
                        success = {
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
                        },
                        failure = ::handleError,
                    )
                }
            }
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
