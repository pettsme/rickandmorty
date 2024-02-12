package com.pettsme.showcase.network.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PageNumberExtractor @Inject constructor() {
    private val pattern = Regex("\\?page=(\\d+)")

    fun getPageNumber(url: String?): Int? =
        pattern.find(url ?: "")?.groupValues?.get(1)?.toIntOrNull()
}
