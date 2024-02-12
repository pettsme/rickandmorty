package com.pettsme.showcase.characterlist.domain.model

internal data class CharacterListItemDomainModel(
    val id: Int,
    val name: String,
    val species: String,
    val vitalStatus: VitalStatus,
    val imageUrl: String,
)
