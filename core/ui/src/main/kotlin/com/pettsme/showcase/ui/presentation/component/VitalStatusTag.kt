package com.pettsme.showcase.ui.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pettsme.showcase.core.domain.model.VitalStatus
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen

@Composable
fun VitalStatusTag(modifier: Modifier = Modifier, vitalStatus: VitalStatus) {
    when (vitalStatus) {
        VitalStatus.ALIVE -> Tag(
            modifier,
            R.string.status_alive,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.onSecondary, // todo material colors could be removed...
        )

        VitalStatus.DEAD -> Tag(
            modifier,
            R.string.status_dead,
            MaterialTheme.colorScheme.error,
            MaterialTheme.colorScheme.onError,
        )

        VitalStatus.UNKNOWN -> Tag(
            modifier,
            R.string.status_unknown,
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun Tag(
    modifier: Modifier,
    @StringRes textRes: Int,
    backgroundColor: Color,
    textColor: Color,
) {
    AppText.Body(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(corner = CornerSize(Dimen.cornerRadiusSmall)),
            )
            .padding(Dimen.spacingQuarter),
        text = stringResource(id = textRes),
        maxLines = 1,
        color = textColor,
    )
}

@Preview
@Composable
private fun VitalStatusAlivePreview() {
    AppTheme {
        VitalStatusTag(vitalStatus = VitalStatus.ALIVE)
    }
}

@Preview
@Composable
private fun VitalStatusDeadPreview() {
    AppTheme {
        VitalStatusTag(vitalStatus = VitalStatus.DEAD)
    }
}

@Preview
@Composable
private fun VitalStatusUnknownPreview() {
    AppTheme {
        VitalStatusTag(vitalStatus = VitalStatus.UNKNOWN)
    }
}
