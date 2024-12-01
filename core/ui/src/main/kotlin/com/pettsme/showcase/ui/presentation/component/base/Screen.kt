package com.pettsme.showcase.ui.presentation.component.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.ui.values.Dimen.paddingDefault

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    contentInsets: WindowInsets = WindowInsets.statusBars,
    content: @Composable BoxScope.(PaddingValues) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(contentInsets),
    ) {
        Column {
            topBar()
            Box(modifier = Modifier.fillMaxSize()) {
                content(
                    PaddingValues(
                        start = paddingDefault,
                        end = paddingDefault,
                    ),
                )
            }
        }
    }
}
