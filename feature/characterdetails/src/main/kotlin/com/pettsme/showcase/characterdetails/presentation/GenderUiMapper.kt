package com.pettsme.showcase.characterdetails.presentation

import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.characterdetails.domain.model.Gender
import com.pettsme.showcase.core.ui.R
import javax.inject.Inject

class GenderUiMapper @Inject constructor(
    stringProvider: StringProvider,
) : StringProvider by stringProvider {

    fun map(gender: Gender): String {
        return when (gender) {
            Gender.MALE -> getString(R.string.character_details_gender_male)
            Gender.FEMALE -> getString(R.string.character_details_gender_female)
            Gender.GENDERLESS -> getString(R.string.character_details_gender_genderless)
            Gender.UNKNOWN -> getString(R.string.character_details_gender_unknown)
        }
    }
}
