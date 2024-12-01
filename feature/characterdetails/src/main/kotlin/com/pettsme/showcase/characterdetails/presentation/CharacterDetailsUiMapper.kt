package com.pettsme.showcase.characterdetails.presentation

import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.domain.model.LocationDetails
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.InfoUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.NameUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.SpeciesGenderUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.StatusUiModel
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationDetailsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel.LocationType
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.vitalstatus.VitalStatusUiMapper
import javax.inject.Inject

internal class CharacterDetailsUiMapper @Inject constructor(
    private val vitalStatusUiMapper: VitalStatusUiMapper,
    private val genderUiMapper: GenderUiMapper,
    stringProvider: StringProvider,
) : StringProvider by stringProvider {

    fun map(character: Character): List<CharacterDetailsUiModel> = with(character) {
        return listOf(
            NameUiModel(
                name = name,
            ),
            InfoUiModel(
                sectionTitle = getString(R.string.character_details_info_title),
                imageUrl = imageUrl,
                description = getString(
                    R.string.character_details_info_description,
                    name,
                    genderUiMapper.map(gender),
                    species,
                    origin.name,
                    lastKnownLocation.name,
                ),
            ),
            StatusUiModel(
                sectionTitle = getString(R.string.character_details_status_title),
                nameLabel = getString(R.string.character_details_status, name),
                vitalStatus = vitalStatusUiMapper.map(status),
            ),
            SpeciesGenderUiModel(
                sectionTitle = getString(R.string.character_details_species_gender_title),
                species = getString(R.string.character_details_species, species),
                gender = getString(R.string.character_details_gender, genderUiMapper.map(gender)),
            ),
            LocationsUiModel(
                sectionTitle = getString(R.string.character_details_origin_location_title),
                locations = listOf(
                    LocationUiModel(
                        id = origin.id,
                        label = getString(R.string.character_details_origin, origin.name),
                        type = LocationType.ORIGIN,
                    ),
                    LocationUiModel(
                        id = lastKnownLocation.id,
                        label = getString(R.string.character_details_origin, lastKnownLocation.name),
                        type = LocationType.LAST_KNOWN,
                    ),
                ),
            ),
            RelatedEpisodesUiModel(
                sectionTitle = getString(R.string.character_details_related_episodes_title),
                episodes = emptyList(),
            ),
        )
    }

    fun map(locationDetails: LocationDetails): LocationDetailsUiModel =
        LocationDetailsUiModel(
            dimension = getString(R.string.character_details_location_dimension, locationDetails.dimension),
            type = getString(R.string.character_details_location_type, locationDetails.type),
        )

    fun map(episodes: List<Episode>): List<EpisodeUiModel> {
        return episodes.map { episode ->
            EpisodeUiModel(
                id = episode.id,
                name = episode.name,
                code = episode.episodeCode,
                aired = getString(R.string.character_details_related_episodes_air_date, episode.aired),
            )
        }
    }
}
