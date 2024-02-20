package com.pettsme.showcase.characterdetails.presentation

import com.pettsme.showcase.characterdetails.domain.model.CharacterDetailsDomainModel
import com.pettsme.showcase.characterdetails.domain.model.EpisodeDomainModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewData
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeViewData
import com.pettsme.showcase.characterdetails.presentation.model.LocationViewData
import com.pettsme.showcase.characterdetails.presentation.model.LocationViewData.LocationType

/**
 * Mapper function for list of episodes
 *
 * This function requires the CharacterDetailsDomainModel to be present, as a context.
 *
 * The context receiver functionality here is rather tried out just for fun,
 * while it kinda makes sense to have this context as this list of episodes only makes sense in
 * that context.
 */
context (CharacterDetailsDomainModel)
internal fun List<EpisodeDomainModel>.toViewData() =
    CharacterDetailsViewData(
        id = id,
        name = name,
        status = status,
        species = species,
        imageUrl = imageUrl,
        gender = gender,
        origin = LocationViewData(
            id = origin.id,
            name = origin.name,
            type = LocationType.ORIGIN,
        ),
        lastKnownLocation = LocationViewData(
            id = lastKnownLocation.id,
            name = lastKnownLocation.name,
            type = LocationType.LAST_KNOWN,
        ),
        presentInEpisodes = map { it.toViewData() },
    )

private fun EpisodeDomainModel.toViewData() =
    EpisodeViewData(
        id = id,
        name = name,
        episodeCode = episodeCode,
        aired = aired,
    )
