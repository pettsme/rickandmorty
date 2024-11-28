package com.pettsme.showcase.characters.domain

import com.pettsme.showcase.characters.data.model.CharacterPreviewApiModel
import com.pettsme.showcase.characters.data.model.CharactersApiModel
import com.pettsme.showcase.characters.domain.model.CharacterPreview
import com.pettsme.showcase.characters.domain.model.CharacterPreviews
import com.pettsme.showcase.core.domain.model.VitalStatus
import com.pettsme.showcase.network.domain.IdExtractor
import javax.inject.Inject

internal class CharactersRepositoryMapper @Inject constructor(
    private val idExtractor: IdExtractor,
) {
    fun map(model: CharactersApiModel): CharacterPreviews =
        CharacterPreviews(
            nextPage = idExtractor.getNumber(IdExtractor.Type.PAGE, model.metaData.next),
            characterPreviews = model.characters.map { it.map() },
        )

    private fun CharacterPreviewApiModel.map(): CharacterPreview =
        CharacterPreview(
            id = id,
            name = name,
            species = species,
            vitalStatus = VitalStatus.fromValue(status),
            imageUrl = image,
            gender = gender,
        )
}
