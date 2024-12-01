package com.pettsme.showcase.ui.presentation.component.vitalstatus

import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.core.domain.model.VitalStatus
import com.pettsme.showcase.core.domain.model.VitalStatus.ALIVE
import com.pettsme.showcase.core.domain.model.VitalStatus.DEAD
import com.pettsme.showcase.core.domain.model.VitalStatus.UNKNOWN
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.vitalstatus.model.VitalStatusUiModel
import com.pettsme.showcase.ui.theme.StaticColors
import javax.inject.Inject

class VitalStatusUiMapper @Inject constructor(
    stringProvider: StringProvider,
) : StringProvider by stringProvider {

    fun map(vitalStatus: VitalStatus): VitalStatusUiModel {
        return when (vitalStatus) {
            ALIVE -> VitalStatusUiModel(
                label = getString(R.string.status_alive),
                textColor = StaticColors.VitalStatusColors.alive.first,
                bgColor = StaticColors.VitalStatusColors.alive.second,
            )

            DEAD -> VitalStatusUiModel(
                label = getString(R.string.status_dead),
                textColor = StaticColors.VitalStatusColors.dead.first,
                bgColor = StaticColors.VitalStatusColors.dead.second,
            )

            UNKNOWN -> VitalStatusUiModel(
                label = getString(R.string.status_unknown),
                textColor = StaticColors.VitalStatusColors.unknown.first,
                bgColor = StaticColors.VitalStatusColors.unknown.second,
            )
        }
    }
}
