package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.characterdetails.data.CharacterDetailsApi
import com.pettsme.showcase.characterdetails.domain.model.CharacterDetailsDomainModel
import com.pettsme.showcase.network.data.asResult
import com.pettsme.showcase.network.data.model.ApiResult
import javax.inject.Inject

internal class CharacterDetailsRepository @Inject constructor(
    private val characterDetailsApi: CharacterDetailsApi,
    private val mapper: CharacterDetailsApiToDomainMapper,
) {
    suspend fun getCharacter(id: Int): ApiResult<CharacterDetailsDomainModel> =
        characterDetailsApi.getCharacter(id).asResult().map { mapper.mapToDomainModel(it) }
}
