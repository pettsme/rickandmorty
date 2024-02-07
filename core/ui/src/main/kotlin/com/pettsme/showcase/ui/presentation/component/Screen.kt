package com.pettsme.showcase.ui.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.base.presentation.model.LoadingState
import com.pettsme.showcase.base.presentation.model.ViewState

@Composable
fun Screen(
    viewState: ViewState,
    modifier: Modifier = Modifier,
    loadingContent: @Composable (BoxScope.() -> Unit)? = null,
    errorContent: @Composable (BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.(isLoading: Boolean) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        if (viewState.errorState == null) {
            errorContent?.invoke(this)
        }

        when (viewState.loadingState) {
            LoadingState.NotLoading -> {
                content(false)
            }

            LoadingState.PullToRefresh -> content(true)
            LoadingState.WholeScreen -> loadingContent?.invoke(this)
        }
    }
}
