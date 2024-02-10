package com.pettsme.showcase.network.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageMetaData(val count: Int, val pages: Int, val next: String?, val prev: String?)
