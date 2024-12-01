package com.pettsme.showcase.core.domain.test

import com.pettsme.showcase.core.domain.model.base.RepositoryError
import com.pettsme.showcase.core.domain.model.base.RepositoryResult
import java.lang.Exception

interface FakeRepository {
    var isError: Boolean

    suspend fun <T> repositoryResult(
        exception: Exception = IllegalStateException(),
        block: () -> T,
    ): RepositoryResult<T> {
        return repositoryResult(
            error = RepositoryError.fromException(exception),
            block = block,
        )
    }

    fun <T> repositoryResult(error: RepositoryError, block: () -> T): RepositoryResult<T> {
        return when (isError) {
            true -> RepositoryResult.error(error)
            false -> RepositoryResult.success(block())
        }
    }
}
