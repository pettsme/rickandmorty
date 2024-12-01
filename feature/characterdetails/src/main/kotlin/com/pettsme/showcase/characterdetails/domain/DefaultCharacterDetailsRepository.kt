package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.base.provider.dispatcher.DispatcherProvider
import com.pettsme.showcase.characterdetails.data.CharacterDetailsApi
import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.core.domain.model.base.RepositoryResult
import com.pettsme.showcase.core.domain.model.base.asRepositoryResult
import com.pettsme.showcase.network.data.model.apiCall
import com.pettsme.showcase.network.data.model.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultCharacterDetailsRepository @Inject constructor(
    private val characterDetailsApi: CharacterDetailsApi,
    private val mapper: CharacterDetailsRepositoryMapper,
    private val dispatcherProvider: DispatcherProvider,
) : CharacterDetailsRepository {
    override suspend fun getCharacter(id: Int): RepositoryResult<Character> =
        withContext(dispatcherProvider.io) {
            apiCall { characterDetailsApi.getCharacter(id) }
                .map(mapper::map)
                .asRepositoryResult()
        }

    override suspend fun getEpisodesForCharacter(
        characterEpisodeIds: String,
    ): RepositoryResult<List<Episode>> =
        withContext(dispatcherProvider.io) {
            apiCall { characterDetailsApi.getEpisodes(characterEpisodeIds) }
                .map(mapper::map)
                .asRepositoryResult()
        }

    override suspend fun getLocationById(id: Int) =
        withContext(dispatcherProvider.io) {
            apiCall { characterDetailsApi.getLocation(id) }
                .map(mapper::map)
                .asRepositoryResult()
        }
}
