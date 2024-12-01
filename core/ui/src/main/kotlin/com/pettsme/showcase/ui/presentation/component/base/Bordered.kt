package com.pettsme.showcase.ui.presentation.component.base

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.pettsme.showcase.ui.theme.ProjectTheme
import com.pettsme.showcase.ui.values.Dimen.cornerRadiusSmall
import com.pettsme.showcase.ui.values.Dimen.lineThin
import com.pettsme.showcase.ui.values.Dimen.paddingQuarter

@Composable
fun Bordered(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadiusSmall))
            .border(
                width = lineThin,
                color = ProjectTheme.colors.primary,
                shape = RoundedCornerShape(cornerRadiusSmall),
            )
            .padding(vertical = paddingQuarter, horizontal = paddingQuarter),
    ) {
        content()
    }
}
