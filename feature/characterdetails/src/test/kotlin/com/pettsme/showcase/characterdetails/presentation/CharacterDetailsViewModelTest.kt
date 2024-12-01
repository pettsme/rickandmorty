package com.pettsme.showcase.characterdetails.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.pettsme.showcase.base.extensions.firstSubtype
import com.pettsme.showcase.base.test.BaseTest
import com.pettsme.showcase.base.test.fakes.FakeStringProvider
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepository
import com.pettsme.showcase.characterdetails.domain.FakeCharacterDetailsRepository
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction.LocationExpanded
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel.LocationType.ORIGIN
import com.pettsme.showcase.ui.presentation.component.vitalstatus.VitalStatusUiMapper
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.test.Test

internal class CharacterDetailsViewModelTest : BaseTest<CharacterDetailsViewModel>() {
    override lateinit var underTest: CharacterDetailsViewModel

    @Test
    fun `given no error when view model initialising then it is setup correctly`() =
        startTest {
            // given-when
            createViewModel()

            // then
            underTest.state.test {
                // first state update is showing loading
                awaitItem().isLoading shouldBe true

                // second state is publishing character details
                with(awaitItem()) {
                    isLoading shouldBe false
                    title shouldBe FakeCharacterDetailsRepository.fakeCharacter.name
                    uiModels.size shouldBe 6
                    uiModels
                        .firstSubtype<CharacterDetailsUiModel, RelatedEpisodesUiModel>()
                        .episodes.size shouldBe 0
                    // testing each ui model mapping can happen in the mapper test
                }

                // finally the third, adding episodes
                awaitItem().uiModels
                    .firstSubtype<CharacterDetailsUiModel, RelatedEpisodesUiModel>()
                    .episodes.size shouldBe 2
            }
        }

    @Test
    fun `given error when view model initialising then state is updated with error`() =
        startTest {
            // given-when
            createViewModel(repo = FakeCharacterDetailsRepository(true))

            // then
            underTest.state.test {
                awaitItem().isLoading shouldBe true
                awaitItem().isError shouldBe true
            }
        }

    @Test
    fun `given no error when location is requested then state is updated correctly`() =
        startTest {
            // given-when
            val locationId = 1
            createViewModel()

            // then
            underTest.state.test {
                // the first 2 state updates previously tested
                skipItems(2)

                // making sure location details is null before requesting
                awaitItem().uiModels.getLocationUiModel(locationId).details shouldBe null

                // when:
                underTest.onViewAction(LocationExpanded(type = ORIGIN, locationId))

                // location details updated with value
                awaitItem().uiModels.getLocationUiModel(locationId) shouldNotBe null
            }
        }

    private fun List<CharacterDetailsUiModel>.getLocationUiModel(id: Int): LocationUiModel {
        return firstSubtype<CharacterDetailsUiModel, LocationsUiModel>()
            .locations.first { it.id == id }
    }

    private fun createViewModel(
        repo: CharacterDetailsRepository = FakeCharacterDetailsRepository(false),
    ) {
        underTest = CharacterDetailsViewModel(
            repository = repo,
            uiMapper = CharacterDetailsUiMapper(
                VitalStatusUiMapper(FakeStringProvider()),
                GenderUiMapper(FakeStringProvider()),
                FakeStringProvider(),
            ),
            stringProvider = FakeStringProvider(),
            savedStateHandle = SavedStateHandle().apply { this["id"] = 0 },
        )
    }
}
