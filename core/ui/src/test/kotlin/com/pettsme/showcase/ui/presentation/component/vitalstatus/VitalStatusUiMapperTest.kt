package com.pettsme.showcase.ui.presentation.component.vitalstatus

import com.pettsme.showcase.base.test.BaseTest
import com.pettsme.showcase.base.test.fakes.FakeStringProvider
import com.pettsme.showcase.core.domain.model.VitalStatus.ALIVE
import com.pettsme.showcase.core.domain.model.VitalStatus.DEAD
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.theme.StaticColors
import io.kotest.matchers.shouldBe
import kotlin.test.Test

internal class VitalStatusUiMapperTest : BaseTest<VitalStatusUiMapper>() {
    override lateinit var underTest: VitalStatusUiMapper

    @Test
    fun `given ALIVE vital statuses when mapping then its correctly mapped`() {
        createUiMapper()

        val result = underTest.map(ALIVE)

        result.label shouldBe R.string.status_alive.toString()
        result.textColor shouldBe StaticColors.VitalStatusColors.alive.first
        result.bgColor shouldBe StaticColors.VitalStatusColors.alive.second
    }

    @Test
    fun `given DEAD vital statuses when mapping then its correctly mapped`() {
        createUiMapper()

        val result = underTest.map(DEAD)

        result.label shouldBe R.string.status_dead.toString()
        result.textColor shouldBe StaticColors.VitalStatusColors.dead.first
        result.bgColor shouldBe StaticColors.VitalStatusColors.dead.second
    }

    // etc

    private fun createUiMapper() {
        underTest = VitalStatusUiMapper(FakeStringProvider())
    }
}
