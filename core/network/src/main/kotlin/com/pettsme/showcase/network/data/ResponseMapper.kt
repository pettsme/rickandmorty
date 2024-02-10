package com.pettsme.showcase.network.data

import com.pettsme.showcase.network.data.exception.ApiException
import com.pettsme.showcase.network.data.model.ApiResult
import retrofit2.Response

fun <T> Response<T>.asResult() =
    when {
        isSuccessful && body() != null -> ApiResult.Success(body()!!)
        else -> ApiResult.Failure(
            ApiException.GeneralApiException(
                errorBody()?.string() ?: "Unknown exception",
            ),
        )
    }
