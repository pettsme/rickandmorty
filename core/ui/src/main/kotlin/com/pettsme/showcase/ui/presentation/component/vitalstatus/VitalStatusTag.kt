package com.pettsme.showcase.ui.presentation.component.vitalstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.presentation.component.vitalstatus.model.VitalStatusUiModel
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen

@Composable
fun VitalStatusTag(
    uiModel: VitalStatusUiModel,
    modifier: Modifier = Modifier,
) {
    AppText.Body(
        modifier = modifier
            .background(
                color = uiModel.bgColor,
                shape = RoundedCornerShape(corner = CornerSize(Dimen.cornerRadiusSmall)),
            )
            .padding(Dimen.paddingQuarter),
        text = uiModel.label,
        maxLines = 1,
        color = uiModel.textColor,
    )
}

@Preview
@Composable
private fun VitalStatusAlive_Preview(
    @PreviewParameter(VitalStatusPreviewProvider::class) model: VitalStatusUiModel,
) {
    AppTheme {
        VitalStatusTag(model)
    }
}
