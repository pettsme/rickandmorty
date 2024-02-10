package com.pettsme.showcase.characterlist.domain

import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.characterlist.data.CharacterListApi
import com.pettsme.showcase.characterlist.data.model.CharacterListApiModel
import com.pettsme.showcase.characterlist.data.model.CharacterListItemApiModel
import com.pettsme.showcase.characterlist.domain.model.CharacterListDomainModel
import com.pettsme.showcase.characterlist.domain.model.CharacterListItemDomainModel
import com.pettsme.showcase.network.data.asResult
import com.pettsme.showcase.network.data.model.ApiResult
import com.pettsme.showcase.network.domain.PageNumberExtractor
import javax.inject.Inject

internal class CharacterListRepository @Inject constructor(
    private val characterListApi: CharacterListApi,
    private val pageNumberExtractor: PageNumberExtractor,
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend fun getCharacters(page: Int = 1): ApiResult<CharacterListDomainModel> =
        characterListApi.getCharacters(page).asResult().map { it.mapToDomainModel() }

    private fun CharacterListApiModel.mapToDomainModel(): CharacterListDomainModel =
        CharacterListDomainModel(
            nextPage = pageNumberExtractor.getPageNumber(info.next),
            characters = results.map { it.mapToDomainModel() },
        )

    private fun CharacterListItemApiModel.mapToDomainModel(): CharacterListItemDomainModel =
        CharacterListItemDomainModel(name = name)
}
