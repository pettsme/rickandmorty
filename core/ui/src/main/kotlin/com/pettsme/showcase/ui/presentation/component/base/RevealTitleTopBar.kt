package com.pettsme.showcase.ui.presentation.component.base

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.ProjectTheme
import com.pettsme.showcase.ui.values.Dimen.spacingHalf
import com.pettsme.showcase.ui.values.Dimen.spacingThreeQuarter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevealTitleTopBar(
    modifier: Modifier = Modifier,
    title: String,
    showTitle: Boolean,
    navigateBack: (() -> Unit)? = null,
    @DrawableRes navigateBackIcon: Int = R.drawable.ic_back,
) {
    TopBar(
        modifier = modifier,
        title = {
            AnimatedVisibility(
                visible = showTitle,
                enter = slideInVertically(initialOffsetY = { it * 2 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it * 2 }) + fadeOut()
            ) {
                AppText.H1(text = title, maxLines = 1)
            }
        },
        navigateBack = navigateBack,
        navigateBackIcon = navigateBackIcon,
    )
}
