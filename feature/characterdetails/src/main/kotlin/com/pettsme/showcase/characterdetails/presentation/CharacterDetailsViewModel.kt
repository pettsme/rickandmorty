package com.pettsme.showcase.characterdetails.presentation

import androidx.lifecycle.SavedStateHandle
import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.base.presentation.model.ErrorState
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepository
import com.pettsme.showcase.characterdetails.domain.model.CharacterDetailsDomainModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewData
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewState
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeViewData
import com.pettsme.showcase.characterdetails.presentation.model.LocationViewData
import com.pettsme.showcase.viewmodelbase.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CharacterDetailsViewModel @Inject constructor(
    private val repository: CharacterDetailsRepository,
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<CharacterDetailsViewState, CharacterDetailsViewAction>(
    CharacterDetailsViewState.initialState,
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
                },
                failure = ::handleError,
            )
        }
    }

    private fun refreshStateWithData() {
        characterDetails?.let { details ->
            launch {
                repository.getEpisodesForCharacter(details).process(
                    success = { episodes ->
                        with(details) {
                            updateState { state ->
                                state.copy(
                                    isLoading = false,
                                    data = CharacterDetailsViewData(
                                        id = id,
                                        name = name,
                                        status = status,
                                        species = species,
                                        imageUrl = imageUrl,
                                        gender = gender,
                                        origin = LocationViewData(
                                            id = origin.id,
                                            name = origin.name,
                                            type = LocationViewData.LocationType.ORIGIN,
                                        ),
                                        lastKnownLocation = LocationViewData(
                                            id = lastKnownLocation.id,
                                            name = lastKnownLocation.name,
                                            type = LocationViewData.LocationType.LAST_KNOWN,
                                        ),
                                        presentInEpisodes = episodes.map {
                                            EpisodeViewData(
                                                id = it.id,
                                                name = it.name,
                                                episodeCode = it.episodeCode,
                                                aired = it.aired,
                                            )
                                        },
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

    override fun onViewAction(viewAction: CharacterDetailsViewAction) {
        when (viewAction) {
            is CharacterDetailsViewAction.LocationExpanded -> {
                launch {
                    repository.getLocationById(viewAction.locationId).process(
                        success = {
                            updateState { state ->
                                if (viewAction.type == LocationViewData.LocationType.ORIGIN) {
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
                    throwable.message ?: "Opps, something went wrong",
                ),
            )
        }
    }
}
