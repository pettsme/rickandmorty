package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pettsme.showcase.ui.values.Dimen

@Composable
internal fun Divider() {
    HorizontalDivider(
        modifier = Modifier.padding(vertical = Dimen.spacingHalf, horizontal = Dimen.spacingNormal),
        thickness = 1.dp,
    )
}
