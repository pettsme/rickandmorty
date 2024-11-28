package com.pettsme.showcase.ui.presentation.component.base

import androidx.annotation.DrawableRes
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
import com.pettsme.showcase.ui.theme.ProjectTheme
import com.pettsme.showcase.ui.values.Dimen.spacingThreeQuarter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigateBack: (() -> Unit)? = null,
    @DrawableRes navigateBackIcon: Int = R.drawable.ic_back,
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = ProjectTheme.colors.background,
        ),
        navigationIcon = navigateBack?.let {
            {
                IconButton(
                    modifier = Modifier.padding(start = spacingThreeQuarter),
                    onClick = navigateBack,
                    content = {
                        Icon(
                            painter = painterResource(navigateBackIcon),
                            contentDescription = stringResource(R.string.content_description_navigate_back),
                        )
                    },
                )
            }
        } ?: {},
    )
}
