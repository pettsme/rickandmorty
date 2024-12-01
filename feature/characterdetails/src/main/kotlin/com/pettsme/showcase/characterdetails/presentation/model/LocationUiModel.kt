package com.pettsme.showcase.characterdetails.presentation.model

data class LocationUiModel(
    val id: Int,
    val label: String,
    val type: LocationType,
    val details: LocationDetailsUiModel? = null,
) {
    enum class LocationType {
        ORIGIN, LAST_KNOWN
    }
}
