package com.pettsme.showcase.characterdetails.presentation

import com.pettsme.showcase.base.test.BaseTest
import com.pettsme.showcase.base.test.fakes.FakeStringProvider
import com.pettsme.showcase.characterdetails.domain.FakeCharacterDetailsRepository.Companion.fakeCharacter
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.InfoUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.NameUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.SpeciesGenderUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.StatusUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel.LocationType.ORIGIN
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.vitalstatus.VitalStatusUiMapper
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlin.test.Test

internal class CharacterDetailsUiMapperTest : BaseTest<CharacterDetailsUiMapper>() {
    override lateinit var underTest: CharacterDetailsUiMapper

    @Test
    fun `given character when mapping it then correct ui state is returned`() {
        // given
        createUiMapper()

        // when
        val result = underTest.map(fakeCharacter)

        // then
        result.size shouldBe 6
        with(result[0]) {
            shouldBeInstanceOf<NameUiModel>()
            name shouldBe fakeCharacter.name
        }
        with(result[1]) {
            shouldBeInstanceOf<InfoUiModel>()
            imageUrl shouldBe fakeCharacter.imageUrl
            description shouldBe
                "${R.string.character_details_info_description}-${fakeCharacter.name}," +
                "${R.string.character_details_gender_male}," +
                "${fakeCharacter.species}," +
                "${fakeCharacter.origin.name}," +
                fakeCharacter.lastKnownLocation.name
        }
        with(result[2]) {
            shouldBeInstanceOf<StatusUiModel>()
            sectionTitle shouldBe R.string.character_details_status_title.toString()
            nameLabel shouldBe "${R.string.character_details_status}-${fakeCharacter.name}"
        }
        with(result[3]) {
            shouldBeInstanceOf<SpeciesGenderUiModel>()
            sectionTitle shouldBe R.string.character_details_species_gender_title.toString()
            species shouldBe "${R.string.character_details_species}-${fakeCharacter.species}"
            gender shouldBe "${R.string.character_details_gender}-${R.string.character_details_gender_male}"
        }
        with(result[4]) {
            shouldBeInstanceOf<LocationsUiModel>()
            sectionTitle shouldBe R.string.character_details_origin_location_title.toString()
            locations.size shouldBe 2
            with(locations[0]) {
                id shouldBe 1
                type shouldBe ORIGIN
                // ....
            }
        }
        with(result[5]) {
            shouldBeInstanceOf<RelatedEpisodesUiModel>()
            // ...
        }
    }

    private fun createUiMapper() {
        underTest = CharacterDetailsUiMapper(
            vitalStatusUiMapper = VitalStatusUiMapper(FakeStringProvider()),
            genderUiMapper = GenderUiMapper(FakeStringProvider()),
            stringProvider = FakeStringProvider(),
        )
    }
}
