package com.pettsme.showcase.characterdetails.domain.model

import com.pettsme.showcase.base.domain.model.VitalStatus
import com.pettsme.showcase.network.domain.model.Location

internal data class CharacterDetailsDomainModel(
    val id: Int,
    val name: String,
    val status: VitalStatus,
    val species: String,
    val gender: Gender,
    val origin: Location,
    val lastKnownLocation: Location,
    val presentInEpisodes: List<Int>,
    val imageUrl: String,
)
