package com.pettsme.showcase.characterlist.domain

import com.pettsme.showcase.characterlist.data.CharacterListApi
import com.pettsme.showcase.characterlist.domain.model.CharacterListDomainModel
import com.pettsme.showcase.network.data.asResult
import com.pettsme.showcase.network.data.model.ApiResult
import javax.inject.Inject

internal class CharacterListRepository @Inject constructor(
    private val characterListApi: CharacterListApi,
    private val mapper: CharacterListApiToDomainMapper,
) {
    suspend fun getCharacters(page: Int): ApiResult<CharacterListDomainModel> =
        characterListApi.getCharacters(page).asResult().map { mapper.mapToDomainModel(it) }
}
