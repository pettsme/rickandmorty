package com.pettsme.showcase.characterdetails.presentation

import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.presentation.model.CharacterUiModel
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel.LocationType
import javax.inject.Inject

internal class CharacterDetailsUiMapper @Inject constructor() {

    fun map(character: Character, episodes: List<Episode> = emptyList()): CharacterUiModel = with(character) {
        CharacterUiModel(
            id = id,
            name = name,
            status = status,
            species = species,
            imageUrl = imageUrl,
            gender = gender,
            origin = LocationUiModel(
                id = origin.id,
                name = origin.name,
                type = LocationType.ORIGIN,
            ),
            lastKnownLocation = LocationUiModel(
                id = lastKnownLocation.id,
                name = lastKnownLocation.name,
                type = LocationType.LAST_KNOWN,
            ),
            presentInEpisodes = episodes.map { it.toViewData() },
        )
    }

    private fun Episode.toViewData() =
        EpisodeUiModel(
            id = id,
            name = name,
            episodeCode = episodeCode,
            aired = aired,
        )
}
