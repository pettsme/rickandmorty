package com.pettsme.showcase.base.presentation.model

sealed interface LoadingState {
    data object NotLoading : LoadingState
    data object WholeScreen : LoadingState
    data object PullToRefresh : LoadingState
}
