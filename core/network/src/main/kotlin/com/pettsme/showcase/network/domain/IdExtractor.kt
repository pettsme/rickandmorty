package com.pettsme.showcase.network.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IdExtractor @Inject constructor() {
    private val pagePattern = Regex("\\?page=(\\d+)")
    private val locationPattern = Regex("location/(\\d+)")
    private val episodePattern = Regex("episode/(\\d+)")

    fun getNumber(type: Type, url: String?): Int? =
        when (type) {
            Type.PAGE -> pagePattern
            Type.LOCATION -> locationPattern
            Type.EPISODE -> episodePattern
        }.find(url ?: "")?.groupValues?.get(1)?.toIntOrNull()

    enum class Type {
        PAGE, LOCATION, EPISODE
    }
}
