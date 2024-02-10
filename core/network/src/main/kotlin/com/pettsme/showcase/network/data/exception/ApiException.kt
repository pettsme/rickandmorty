package com.pettsme.showcase.network.data.exception

sealed class ApiException(override val message: String) : Throwable(message = message) {
    data class GeneralApiException(override val message: String) : ApiException(message)
}
