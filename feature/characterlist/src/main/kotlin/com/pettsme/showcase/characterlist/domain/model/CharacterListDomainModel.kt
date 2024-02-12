package com.pettsme.showcase.characterlist.domain.model

internal data class CharacterListDomainModel(
    val nextPage: Int?,
    val characters: List<CharacterListItemDomainModel>,
)
