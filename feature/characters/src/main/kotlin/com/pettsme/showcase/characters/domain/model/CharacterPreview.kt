package com.pettsme.showcase.characters.domain.model

import com.pettsme.showcase.base.domain.model.VitalStatus

internal data class CharacterPreview(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val vitalStatus: VitalStatus,
    val imageUrl: String,
)
