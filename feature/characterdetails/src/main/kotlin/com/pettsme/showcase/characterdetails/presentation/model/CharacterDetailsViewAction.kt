package com.pettsme.showcase.characterdetails.presentation.model

import com.pettsme.showcase.characterdetails.presentation.model.LocationViewData.LocationType

internal sealed interface CharacterDetailsViewAction {
    data class LocationExpanded(val type: LocationType, val locationId: Int) :
        CharacterDetailsViewAction
}
