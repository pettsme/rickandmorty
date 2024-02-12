package com.pettsme.showcase.characterdetails.domain.model

enum class Gender(val value: String) {
    MALE("Male"),
    FEMALE("Female"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown");

    companion object {
        fun fromValue(value: String): Gender {
            return Gender.entries.firstOrNull { it.value == value.lowercase() } ?: UNKNOWN
        }
    }
}
