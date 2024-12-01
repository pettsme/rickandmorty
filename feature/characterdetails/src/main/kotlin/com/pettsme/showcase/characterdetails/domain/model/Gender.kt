package com.pettsme.showcase.characterdetails.domain.model

/**
 * Value used for mapping from the API but ideally this will be gone with kotlinx.serializetion
 * as that can directly map into enums a lot easier than moshi (todo)
 *
 * until then value is not to be used by UI! (got a UiMapper for that
 */
enum class Gender(@Deprecated("not to be used") val value: String) {
    MALE("Male"),
    FEMALE("Female"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown"),
    ;

    companion object {
        fun fromValue(value: String): Gender {
            return Gender.entries.firstOrNull { it.value == value.lowercase() } ?: UNKNOWN
        }
    }
}
