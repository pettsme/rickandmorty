package com.pettsme.showcase.characterdetails.domain

import com.pettsme.showcase.characterdetails.domain.model.Character
import com.pettsme.showcase.characterdetails.domain.model.Episode
import com.pettsme.showcase.characterdetails.domain.model.Gender
import com.pettsme.showcase.characterdetails.domain.model.Location
import com.pettsme.showcase.characterdetails.domain.model.LocationDetails
import com.pettsme.showcase.core.domain.model.VitalStatus.ALIVE
import com.pettsme.showcase.core.domain.model.base.RepositoryResult
import com.pettsme.showcase.core.domain.test.FakeRepository

internal class FakeCharacterDetailsRepository(
    override var isError: Boolean,
) : CharacterDetailsRepository, FakeRepository {
    override suspend fun getCharacter(id: Int): RepositoryResult<Character> =
        repositoryResult { fakeCharacter }

    override suspend fun getEpisodesForCharacter(characterEpisodeIds: String): RepositoryResult<List<Episode>> =
        repositoryResult { fakeListOfEpisodes }

    override suspend fun getLocationById(id: Int): RepositoryResult<LocationDetails> =
        repositoryResult { fakeLocationDetails }

    companion object {
        val fakeCharacter = Character(
            id = 0,
            name = "Rick",
            status = ALIVE,
            species = "Human",
            gender = Gender.MALE,
            origin = Location(
                id = 1,
                name = "Earth",
            ),
            lastKnownLocation = Location(
                id = 2,
                name = "Citadel of Ricks",
            ),
            presentInEpisodes = listOf(1, 2, 3),
            imageUrl = "https://image.url",
        )

        val fakeListOfEpisodes = listOf(
            Episode(
                id = 1,
                name = "Episode 1",
                aired = "4th of July, 2000",
                episodeCode = "S01E01",
            ),
            Episode(
                id = 2,
                name = "Episode 2",
                aired = "19th of July, 2000",
                episodeCode = "S01E02",
            ),
        )
        val fakeLocationDetails = LocationDetails(
            id = 1,
            name = "Earth",
            type = "Planet",
            dimension = "C01",
        )
    }
}
