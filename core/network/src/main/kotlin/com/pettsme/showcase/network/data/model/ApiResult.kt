package com.pettsme.showcase.network.data.model

import com.pettsme.showcase.network.data.exception.ApiException.GeneralApiException
import com.pettsme.showcase.network.data.model.ApiResult.Failure
import com.pettsme.showcase.network.data.model.ApiResult.Success
import retrofit2.Response

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>
    data class Failure(val throwable: Throwable, val responseCode: Int?) : ApiResult<Nothing>
}

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = call.invoke()
        return apiResult(response)
    } catch (e: Exception) {
        Failure(e, null)
    }
}

private fun <T : Any> apiResult(response: Response<T>): ApiResult<T> {
    return when {
        response.isSuccessful -> Success(response.body()!!)
        // Could add here any response code checks, Unauthorized etc
        else -> Failure(
            GeneralApiException(
                response.errorBody()?.string() ?: "Unknown API level exception",
            ),
            response.code(),
        )
    }
}

inline fun <T, R> ApiResult<T>.map(transform: (T) -> R): ApiResult<R> {
    return when (this) {
        is Success -> Success(transform(this.data))
        is Failure -> this
    }
}
