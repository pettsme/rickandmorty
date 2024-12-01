package com.pettsme.showcase.characters.domain

import com.pettsme.showcase.base.provider.dispatcher.DispatcherProvider
import com.pettsme.showcase.characters.data.CharacterListApi
import com.pettsme.showcase.characters.domain.model.CharacterPreviews
import com.pettsme.showcase.core.domain.model.base.RepositoryResult
import com.pettsme.showcase.core.domain.model.base.asRepositoryResult
import com.pettsme.showcase.network.data.model.apiCall
import com.pettsme.showcase.network.data.model.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CharactersRepository @Inject constructor(
    private val characterListApi: CharacterListApi,
    private val mapper: CharactersRepositoryMapper,
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend fun getCharacters(page: Int): RepositoryResult<CharacterPreviews> =
        withContext(dispatcherProvider.io) {
            apiCall { characterListApi.getCharacters(page) }
                .map(mapper::map)
                .asRepositoryResult()
        }
}
