package com.pettsme.showcase.network.data.model

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure(val throwable: Throwable) : ApiResult<Nothing>()

    fun <R> map(transform: (T) -> R): ApiResult<R> = when (this) {
        is Success -> Success(transform(this.data))
        is Failure -> this
    }

    fun process(
        success: (T) -> Unit,
        failure: (Throwable) -> Unit,
    ) {
        when (this) {
            is Success -> success(data)
            is Failure -> failure(throwable)
        }
    }
}
