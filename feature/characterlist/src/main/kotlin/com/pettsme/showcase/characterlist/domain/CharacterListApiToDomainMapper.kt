package com.pettsme.showcase.characterlist.domain

import com.pettsme.showcase.characterlist.data.model.CharacterListApiModel
import com.pettsme.showcase.characterlist.data.model.CharacterListItemApiModel
import com.pettsme.showcase.characterlist.domain.model.CharacterListDomainModel
import com.pettsme.showcase.characterlist.domain.model.CharacterListItemDomainModel
import com.pettsme.showcase.characterlist.domain.model.VitalStatus
import com.pettsme.showcase.network.domain.PageNumberExtractor
import javax.inject.Inject

internal class CharacterListApiToDomainMapper @Inject constructor(
    private val pageNumberExtractor: PageNumberExtractor,
) {
    fun mapToDomainModel(model: CharacterListApiModel): CharacterListDomainModel =
        CharacterListDomainModel(
            nextPage = pageNumberExtractor.getPageNumber(model.info.next),
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
