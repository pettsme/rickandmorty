package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.characterdetails.data.model.CharacterDetailsApiModel
import com.pettsme.showcase.characterdetails.data.model.EpisodeApiModel
import com.pettsme.showcase.characterdetails.data.model.FullLocationApiModel
import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.domain.model.FullLocation
import com.pettsme.showcase.characterdetails.domain.model.Gender
import com.pettsme.showcase.characterdetails.domain.model.Location
import com.pettsme.showcase.core.domain.model.VitalStatus
import com.pettsme.showcase.network.data.model.LocationApiModel
import com.pettsme.showcase.network.domain.IdExtractor
import javax.inject.Inject

internal class CharacterDetailsRepositoryMapper @Inject constructor(
    private val idExtractor: IdExtractor,
) {
    fun map(model: CharacterDetailsApiModel): Character =
        with(model) {
            Character(
                id = id,
                name = name,
                gender = Gender.fromValue(gender),
                status = VitalStatus.fromValue(status),
                species = species,
                origin = map(origin),
                lastKnownLocation = map(location),
                presentInEpisodes = episode.map {
                    idExtractor.getNumber(IdExtractor.Type.EPISODE, it) ?: -1
                },
                imageUrl = image,
            )
        }

    fun map(listOfEpisodes: List<EpisodeApiModel>): List<Episode> =
        listOfEpisodes.map { episode ->
            Episode(
                id = episode.id,
                name = episode.name,
                aired = episode.airDate,
                episodeCode = episode.episode,
            )
        }

    private fun map(locationApiModel: LocationApiModel) = Location(
        name = locationApiModel.name,
        id = idExtractor.getNumber(IdExtractor.Type.LOCATION, locationApiModel.url)
            ?: -1, // this can happen when the location is "unknown"
    )

    fun map(location: FullLocationApiModel) = FullLocation(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
    )
}
