package com.pettsme.showcase.network.domain

import javax.inject.Inject
import javax.inject.Singleton

/**
 * This API references different connected data with URLs, instead of IDs.
 * Examples for the above:
 *
 * character endpoint, character object, episodes list:
 * // ...
 * "episode": [
 *     "https://rickandmortyapi.com/api/episode/1",
 *     "https://rickandmortyapi.com/api/episode/2",
 *     // ...
 *   ],
 * // ...
 *
 * Or the location object for last known and origin locations:
 * // ...
 * "location": {
 *     "name": "Earth",
 *     "url": "https://rickandmortyapi.com/api/location/20"
 *   },
 *   // ...
 *
 *   Due to this, the IDs for locations, episodes and even for pages need to be extracted
 *   from the given URLs.
 *
 *   This class encapsulates this logic, can be used for pages, locations and episodes.
 *
 */
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
