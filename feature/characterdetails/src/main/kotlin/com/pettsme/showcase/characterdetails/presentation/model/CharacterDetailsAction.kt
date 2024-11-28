package com.pettsme.showcase.characterdetails.presentation.model

import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel.LocationType

internal sealed interface CharacterDetailsAction {
    data class LocationExpanded(val type: LocationType, val locationId: Int) :
        CharacterDetailsAction
}
