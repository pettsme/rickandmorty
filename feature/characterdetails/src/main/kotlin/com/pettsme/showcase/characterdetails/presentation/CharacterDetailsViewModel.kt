package com.pettsme.showcase.characterdetails.presentation

import androidx.lifecycle.SavedStateHandle
import com.pettsme.showcase.base.extensions.updateItem
import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepository
import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.domain.model.LocationDetails
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction.LocationExpanded
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsState
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
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
) : BaseViewModel<CharacterDetailsState, CharacterDetailsAction>(
    CharacterDetailsState.initialState,
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
                title = character.name,
                uiModels = uiMapper.map(character),
            )
        }
        getEpisodesForCharacter(character.presentInEpisodesIds)
    }

    private fun getEpisodesForCharacter(episodeIds: String) {
        launch {
            repository.getEpisodesForCharacter(episodeIds)
                .onSuccess(::onEpisodesLoaded)
                .onError(::handleError)
        }
    }

    private fun onEpisodesLoaded(episodeList: List<Episode>) {
        val episodeUiModels = uiMapper.map(episodeList)
        updateState { state ->
            state.copy(
                uiModels = state.uiModels.updateItem(
                    predicate = { it is RelatedEpisodesUiModel },
                    transform = { uiModel ->
                        (uiModel as RelatedEpisodesUiModel).copy(episodes = episodeUiModels)
                    },
                ),
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
                        .onSuccess { onLocationLoaded(viewAction, it) }
                        .onError(::handleError)
                }
            }
        }
    }

    private fun onLocationLoaded(
        viewAction: LocationExpanded,
        locationDetails: LocationDetails,
    ) {
        updateState { state ->
            state.copy(
                // first we locate the locations ui model, then the location type- hence two updates
                uiModels = state.uiModels.updateItem(
                    predicate = { it is LocationsUiModel },
                    transform = { uiModel ->
                        val locationsUiModel = uiModel as LocationsUiModel
                        locationsUiModel.copy(
                            locations = locationsUiModel.locations.updateItem(
                                predicate = { it.type == viewAction.type },
                                transform = { location ->
                                    location.copy(
                                        details = uiMapper.map(locationDetails),
                                    )
                                },
                            ),
                        )
                    },
                ),
            )
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
