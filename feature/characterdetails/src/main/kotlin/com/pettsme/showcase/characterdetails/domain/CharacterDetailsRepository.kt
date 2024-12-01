package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.domain.model.LocationDetails
import com.pettsme.showcase.core.domain.model.base.RepositoryResult

internal interface CharacterDetailsRepository {
    suspend fun getCharacter(id: Int): RepositoryResult<Character>

    suspend fun getEpisodesForCharacter(
        characterEpisodeIds: String,
    ): RepositoryResult<List<Episode>>

    suspend fun getLocationById(id: Int): RepositoryResult<LocationDetails>
}
