package com.pettsme.showcase.characterlist.domain

import com.pettsme.showcase.characterlist.data.model.CharacterListApiModel
import com.pettsme.showcase.characterlist.data.model.CharacterListItemApiModel
import com.pettsme.showcase.characterlist.domain.model.CharacterListDomainModel
import com.pettsme.showcase.characterlist.domain.model.CharacterListItemDomainModel
import com.pettsme.showcase.base.domain.model.VitalStatus
import com.pettsme.showcase.network.domain.IdExtractor
import javax.inject.Inject

internal class CharacterListApiToDomainMapper @Inject constructor(
    private val idExtractor: IdExtractor,
) {
    fun mapToDomainModel(model: CharacterListApiModel): CharacterListDomainModel =
        CharacterListDomainModel(
            nextPage = idExtractor.getNumber(IdExtractor.Type.PAGE, model.info.next),
            characters = model.results.map { it.mapToDomainModel() },
        )

    private fun CharacterListItemApiModel.mapToDomainModel(): CharacterListItemDomainModel =
        CharacterListItemDomainModel(
            id = id,
            name = name,
            species = species,
            vitalStatus = VitalStatus.fromValue(status),
            imageUrl = image,
        )
}
