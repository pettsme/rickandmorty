package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.base.domain.model.VitalStatus
import com.pettsme.showcase.characterdetails.data.model.CharacterDetailsApiModel
import com.pettsme.showcase.characterdetails.data.model.EpisodeApiModel
import com.pettsme.showcase.characterdetails.data.model.FullLocationApiModel
import com.pettsme.showcase.characterdetails.domain.model.CharacterDetailsDomainModel
import com.pettsme.showcase.characterdetails.domain.model.EpisodeDomainModel
import com.pettsme.showcase.characterdetails.domain.model.FullLocation
import com.pettsme.showcase.characterdetails.domain.model.Gender
import com.pettsme.showcase.network.data.model.LocationApiModel
import com.pettsme.showcase.network.domain.IdExtractor
import com.pettsme.showcase.network.domain.model.Location
import javax.inject.Inject

internal class CharacterDetailsApiToDomainMapper @Inject constructor(
    private val idExtractor: IdExtractor,
) {
    fun mapToDomainModel(model: CharacterDetailsApiModel): CharacterDetailsDomainModel =
        with(model) {
            CharacterDetailsDomainModel(
                id = id,
                name = name,
                gender = Gender.fromValue(gender),
                status = VitalStatus.fromValue(status),
                species = species,
                origin = mapToDomainModel(origin),
                lastKnownLocation = mapToDomainModel(location),
                presentInEpisodes = episode.map {
                    idExtractor.getNumber(IdExtractor.Type.EPISODE, it) ?: -1
                },
                imageUrl = image,
            )
        }

    fun mapToDomainModel(listOfEpisodes: List<EpisodeApiModel>): List<EpisodeDomainModel> =
        listOfEpisodes.map { episode ->
            EpisodeDomainModel(
                id = episode.id,
                name = episode.name,
                aired = episode.airDate,
                episodeCode = episode.episode,
            )
        }

    private fun mapToDomainModel(locationApiModel: LocationApiModel) = Location(
        name = locationApiModel.name,
        id = idExtractor.getNumber(IdExtractor.Type.LOCATION, locationApiModel.url)
            ?: -1, // this can happen when the location is "unknown"
    )

    fun mapToDomainModel(location: FullLocationApiModel) = FullLocation(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
    )
}
