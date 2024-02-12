package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.characterdetails.data.model.CharacterDetailsApiModel
import com.pettsme.showcase.characterdetails.domain.model.CharacterDetailsDomainModel
import javax.inject.Inject

internal class CharacterDetailsApiToDomainMapper @Inject constructor() {
    fun mapToDomainModel(model: CharacterDetailsApiModel): CharacterDetailsDomainModel =
        CharacterDetailsDomainModel(
            id = model.id,
            name = model.name,
        )
}
