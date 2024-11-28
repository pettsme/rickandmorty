package com.pettsme.showcase.ui.presentation.component.base

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.ProjectTheme

@Composable
fun SimpleTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    navigateBack: (() -> Unit)? = null,
    @DrawableRes navigateBackIcon: Int = R.drawable.ic_back,
) {
    TopBar(
        modifier = modifier,
        title = { AppText.H1(text = title, color = ProjectTheme.colors.primary, maxLines = 1) },
        navigateBack = navigateBack,
        navigateBackIcon = navigateBackIcon,
    )
}
