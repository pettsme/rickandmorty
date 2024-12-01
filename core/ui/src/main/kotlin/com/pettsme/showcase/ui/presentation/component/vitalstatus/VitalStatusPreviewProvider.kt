package com.pettsme.showcase.ui.presentation.component.vitalstatus

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pettsme.showcase.ui.presentation.component.vitalstatus.model.VitalStatusUiModel
import com.pettsme.showcase.ui.theme.StaticColors

/**
 * This is only used for previewing the VitalStatusTag..
 */
internal class VitalStatusPreviewProvider : PreviewParameterProvider<VitalStatusUiModel> {
    override val values: Sequence<VitalStatusUiModel> = sequenceOf(
        VitalStatusUiModel(
            label = "ALIVE",
            textColor = StaticColors.VitalStatusColors.alive.first,
            bgColor = StaticColors.VitalStatusColors.alive.second,
        ),

        VitalStatusUiModel(
            label = "DEAD",
            textColor = StaticColors.VitalStatusColors.dead.first,
            bgColor = StaticColors.VitalStatusColors.dead.second,
        ),

        VitalStatusUiModel(
            label = "UNKNOWN",
            textColor = StaticColors.VitalStatusColors.unknown.first,
            bgColor = StaticColors.VitalStatusColors.unknown.second,
        ),
    )
}
