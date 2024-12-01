package com.pettsme.showcase.characterdetails.presentation.model

import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.InfoUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.NameUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.SpeciesGenderUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.StatusUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel.LocationType
import com.pettsme.showcase.ui.presentation.component.vitalstatus.model.VitalStatusUiModel
import com.pettsme.showcase.ui.theme.StaticColors
import java.util.UUID

internal sealed class CharacterDetailsUiModel(
    open val sectionTitle: String,
    val id: String = UUID.randomUUID().toString(),
) {
    data class NameUiModel(
        val name: String,
    ) : CharacterDetailsUiModel("")

    data class InfoUiModel(
        override val sectionTitle: String,
        val imageUrl: String,
        val description: String,
    ) : CharacterDetailsUiModel(sectionTitle)

    data class StatusUiModel(
        override val sectionTitle: String,
        val nameLabel: String,
        val vitalStatus: VitalStatusUiModel,
    ) : CharacterDetailsUiModel(sectionTitle)

    data class SpeciesGenderUiModel(
        override val sectionTitle: String,
        val species: String,
        val gender: String,
    ) : CharacterDetailsUiModel(sectionTitle)

    data class LocationsUiModel(
        override val sectionTitle: String,
        val locations: List<LocationUiModel>,
    ) : CharacterDetailsUiModel(sectionTitle)

    data class RelatedEpisodesUiModel(
        override val sectionTitle: String,
        val episodes: List<EpisodeUiModel>,
    ) : CharacterDetailsUiModel(sectionTitle)
}

internal val fakeCharacterDetailUiModels = listOf(
    NameUiModel(
        name = "Rick Sanchez",
    ),
    InfoUiModel(
        sectionTitle = "Character Info",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        description = "Rick Sanchez is a genius scientist known for his reckless, " +
            "nihilistic behavior and his complex relationship with his family. " +
            "Often seen as cynical and eccentric, he possesses a vast knowledge of the universe.",
    ),
    StatusUiModel(
        sectionTitle = "Character Status",
        nameLabel = "Rick Sanchez is",
        vitalStatus = VitalStatusUiModel(
            label = "ALIVE",
            bgColor = StaticColors.VitalStatusColors.alive.first,
            textColor = StaticColors.VitalStatusColors.alive.second,
        ),
    ),
    SpeciesGenderUiModel(
        sectionTitle = "Species & Gender",
        species = "Human",
        gender = "Male",
    ),
    LocationsUiModel(
        sectionTitle = "Origin and Location",
        locations = listOf(
            LocationUiModel(
                id = 1,
                label = "Earth",
                type = LocationType.ORIGIN,
            ),
            LocationUiModel(
                id = 2,
                label = "Citadel of Ricks",
                type = LocationType.LAST_KNOWN,
            ),
        ),
    ),
    RelatedEpisodesUiModel(
        sectionTitle = "Related Episodes",
        episodes = listOf(
            EpisodeUiModel(
                id = 1,
                name = "Pilot",
                code = "S01E01",
                aired = "Dec 2, 2013",
            ),
            EpisodeUiModel(
                id = 2,
                name = "Lawnmower",
                code = "S01E02",
                aired = "Dec 9, 2013",
            ),
            EpisodeUiModel(
                id = 3,
                name = "Anatomy Park",
                code = "S01E03",
                aired = "Dec 16, 2013",
            ),
        ),
    ),
)
