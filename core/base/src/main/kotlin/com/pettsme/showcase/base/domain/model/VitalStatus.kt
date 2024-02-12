package com.pettsme.showcase.base.domain.model

enum class VitalStatus(val value: String) {
    ALIVE("alive"),
    DEAD("dead"),
    UNKNOWN("unknown"),
    ;

    companion object {
        fun fromValue(value: String): VitalStatus {
            return entries.firstOrNull { it.value == value.lowercase() } ?: UNKNOWN
        }
    }
}
