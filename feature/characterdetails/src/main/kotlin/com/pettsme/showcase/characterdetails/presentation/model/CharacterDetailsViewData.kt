package com.pettsme.showcase.characterdetails.presentation.model

import com.pettsme.showcase.base.domain.model.VitalStatus
import com.pettsme.showcase.characterdetails.domain.model.FullLocation
import com.pettsme.showcase.characterdetails.domain.model.Gender

internal data class CharacterDetailsViewData(
    val id: Int,
    val name: String,
    val status: VitalStatus,
    val species: String,
    val imageUrl: String,
    val gender: Gender, // domain model leak in view data
    val origin: LocationViewData,
    val lastKnownLocation: LocationViewData,
    val presentInEpisodes: List<EpisodeViewData>,
    val originFullLocation: FullLocation? = null,
    val lastKnownFullLocation: FullLocation? = null,
)

internal val fakeCharacterDetailsViewData = CharacterDetailsViewData(
    id = 2,
    name = "Morty Smith",
    status = VitalStatus.ALIVE,
    species = "Human",
    imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
    gender = Gender.MALE,
    origin = LocationViewData(1, "Earth", LocationViewData.LocationType.ORIGIN),
    lastKnownLocation = LocationViewData(1, "Earth", LocationViewData.LocationType.LAST_KNOWN),
    presentInEpisodes = listOf(
        EpisodeViewData(
            id = 10,
            name = "Close Rick-counters of the Rick Kind",
            episodeCode = "S01E10",
            aired = "April 7, 2014",
        ),
        EpisodeViewData(
            id = 28,
            name = "The Ricklantis Mixup",
            episodeCode = "S03E07",
            aired = "September 10, 2017",
        ),
    ),
)
