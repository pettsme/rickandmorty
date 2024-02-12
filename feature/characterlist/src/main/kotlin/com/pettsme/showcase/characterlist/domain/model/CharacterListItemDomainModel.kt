package com.pettsme.showcase.characterlist.domain.model

import com.pettsme.showcase.base.domain.model.VitalStatus

internal data class CharacterListItemDomainModel(
    val id: Int,
    val name: String,
    val species: String,
    val vitalStatus: VitalStatus,
    val imageUrl: String,
)
