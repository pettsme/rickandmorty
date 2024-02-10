package com.pettsme.showcase.characterlist.domain.model

data class CharacterListDomainModel(
    val nextPage: Int,
    val characters: List<CharacterListItemDomainModel>,
)
