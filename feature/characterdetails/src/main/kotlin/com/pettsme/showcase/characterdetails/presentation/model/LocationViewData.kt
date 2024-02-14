package com.pettsme.showcase.characterdetails.presentation.model

data class LocationViewData(
    val id: Int,
    val name: String,
    val type: LocationType,
) {
    enum class LocationType {
        ORIGIN, LAST_KNOWN
    }
}
