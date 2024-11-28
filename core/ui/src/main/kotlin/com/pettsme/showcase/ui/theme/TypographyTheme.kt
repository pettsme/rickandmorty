package com.pettsme.showcase.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.pettsme.showcase.core.ui.R

internal val DefaultFontFamily = FontFamily(
    Font(resId = R.font.veritas_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(resId = R.font.veritas_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(resId = R.font.veritas_medium, weight = FontWeight.Bold, style = FontStyle.Normal),
)

internal val materialTypography = Typography(
    headlineMedium = DefaultAppTypography.h1,
    titleMedium = DefaultAppTypography.h2,
    bodyMedium = DefaultAppTypography.body,
)
