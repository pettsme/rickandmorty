package com.pettsme.showcase.characterdetails.domain.model

import com.pettsme.showcase.core.domain.model.VitalStatus

internal data class Character(
    val id: Int,
    val name: String,
    val status: VitalStatus,
    val species: String,
    val gender: Gender,
    val origin: Location,
    val lastKnownLocation: Location,
    val presentInEpisodes: List<Int>,
    val imageUrl: String,
) {
    val presentInEpisodesIds = presentInEpisodes.joinToString(",")
}
