package com.pettsme.showcase.characters.domain

import com.pettsme.showcase.characters.data.CharacterListApi
import com.pettsme.showcase.characters.domain.model.CharacterPreviews
import com.pettsme.showcase.network.data.asResult
import com.pettsme.showcase.network.data.model.ApiResult
import javax.inject.Inject

internal class CharactersRepository @Inject constructor(
    private val characterListApi: CharacterListApi,
    private val mapper: CharactersRepositoryMapper,
) {
    suspend fun getCharacters(page: Int): ApiResult<CharacterPreviews> =
        characterListApi.getCharacters(page).asResult().map { mapper.map(it) }
}
