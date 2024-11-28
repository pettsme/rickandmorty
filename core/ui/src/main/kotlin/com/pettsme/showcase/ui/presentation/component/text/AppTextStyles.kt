package com.pettsme.showcase.ui.presentation.component.text

import com.pettsme.showcase.ui.theme.DefaultAppTypography

internal fun AppText.asTextStyle() = when (this) {
    AppText.H1 -> DefaultAppTypography.h1
    AppText.H2 -> DefaultAppTypography.h2
    AppText.Body -> DefaultAppTypography.body
}
