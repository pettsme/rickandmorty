package com.pettsme.showcase.core.domain.model.base

import com.pettsme.showcase.network.data.model.ApiResult
import com.pettsme.showcase.network.data.model.ApiResult.Failure
import com.pettsme.showcase.network.data.model.ApiResult.Success

class RepositoryResult<T> private constructor() {
    var result: T? = null
        private set

    var error: RepositoryError? = null
        private set

    // for deconstruction if needed
    operator fun component1(): T? = result

    // for deconstruction if needed
    operator fun component2(): RepositoryError? = error

    companion object {
        fun <T> success(result: T): RepositoryResult<T> = RepositoryResult<T>().apply {
            this.result = result
        }

        fun <T> error(error: RepositoryError) = RepositoryResult<T>().apply {
            this.error = error
        }
    }
}

fun <T> ApiResult<T>.asRepositoryResult(): RepositoryResult<T> {
    return when (this) {
        is Failure -> RepositoryResult.error(
            RepositoryError.fromException(throwable, responseCode = responseCode),
        )

        is Success -> RepositoryResult.success(data)
    }
}

inline fun <T> RepositoryResult<T>.onSuccess(block: (result: T) -> Unit): RepositoryResult<T> {
    if (result != null) {
        block(result!!)
    }
    return this
}

inline fun <T> RepositoryResult<T>.onError(block: (error: RepositoryError) -> Unit): RepositoryResult<T> {
    if (error != null) {
        block(error!!)
    }
    return this
}
