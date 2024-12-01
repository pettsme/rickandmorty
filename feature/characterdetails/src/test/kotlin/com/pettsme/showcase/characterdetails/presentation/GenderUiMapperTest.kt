package com.pettsme.showcase.characterdetails.presentation

import com.pettsme.showcase.base.test.BaseTest
import com.pettsme.showcase.base.test.fakes.FakeStringProvider
import com.pettsme.showcase.characterdetails.domain.model.Gender.FEMALE
import com.pettsme.showcase.characterdetails.domain.model.Gender.GENDERLESS
import com.pettsme.showcase.characterdetails.domain.model.Gender.MALE
import com.pettsme.showcase.characterdetails.domain.model.Gender.UNKNOWN
import com.pettsme.showcase.core.ui.R
import io.kotest.matchers.shouldBe
import kotlin.test.Test

// This is a simple testing of different gender mapping,
// no need to call startTest() as we aren't using coroutines
internal class GenderUiMapperTest : BaseTest<GenderUiMapper>() {
    override lateinit var underTest: GenderUiMapper

    @Test
    fun `given female gender when mapping it to the UI then we get correct string`() {
        // given
        createUiMapper()

        // when
        val result = underTest.map(FEMALE)

        // then
        result shouldBe R.string.character_details_gender_female.toString()
    }

    @Test
    fun `given male gender when mapping it to the UI then we get correct string`() {
        // given
        createUiMapper()

        // when
        val result = underTest.map(MALE)

        // then
        result shouldBe R.string.character_details_gender_male.toString()
    }

    @Test
    fun `given genderless gender when mapping it to the UI then we get correct string`() {
        // given
        createUiMapper()

        // when
        val result = underTest.map(GENDERLESS)

        // then
        result shouldBe R.string.character_details_gender_genderless.toString()
    }

    @Test
    fun `given any other gender when mapping it to the UI then we get correct string`() {
        // given
        createUiMapper()

        // when
        val result = underTest.map(UNKNOWN)

        // then
        result shouldBe R.string.character_details_gender_unknown.toString()
    }

    private fun createUiMapper() {
        underTest = GenderUiMapper(
            stringProvider = FakeStringProvider(),
        )
    }
}
