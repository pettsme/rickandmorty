package com.pettsme.showcase.characters.presentation

import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characters.domain.model.CharacterPreview
import com.pettsme.showcase.characters.presentation.model.CharactersUiModel.CharacterPreviewUiModel
import com.pettsme.showcase.core.ui.R
import javax.inject.Inject

internal class CharactersUiMapper @Inject constructor(
    stringProvider: StringProvider,
) : StringProvider by stringProvider {

    fun map(character: CharacterPreview) = CharacterPreviewUiModel(
        id = character.id,
        name = character.name,
        description = getString(
            stringId = R.string.characters_character_description,
            character.name,
            character.species.lowercase(),
            character.gender.lowercase(),
        ),
        imageUrl = character.imageUrl,
    )
}
