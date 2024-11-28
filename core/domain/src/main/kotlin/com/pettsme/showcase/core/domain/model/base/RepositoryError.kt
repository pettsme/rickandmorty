package com.pettsme.showcase.core.domain.model.base

import com.pettsme.showcase.network.data.exception.ApiException.GeneralApiException
import java.io.IOException
import java.net.SocketTimeoutException

sealed class RepositoryError(val throwable: Throwable, val description: String? = null) {
    class Network(cause: Throwable) : RepositoryError(cause)

    class Server(cause: Throwable, description: String? = null, val responseCode: Int? = null) :
        RepositoryError(cause, description)

    class Generic(cause: Throwable, description: String? = null) :
        RepositoryError(cause, description)

    companion object {
        fun fromException(
            throwable: Throwable,
            description: String? = null,
            responseCode: Int? = null,
        ): RepositoryError {
            return when (throwable) {
                is GeneralApiException, is SocketTimeoutException -> Server(
                    throwable,
                    description,
                    responseCode,
                )

                is IOException -> Network(throwable)
                else -> Generic(throwable, description)
            }
        }
    }
}
