package com.pettsme.showcase.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
inline fun Modifier.applyIf(
    condition: Boolean,
    crossinline other: @Composable Modifier.() -> Modifier,
) = if (condition) other() else this
