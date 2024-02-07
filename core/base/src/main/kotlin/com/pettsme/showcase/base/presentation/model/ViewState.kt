package com.pettsme.showcase.base.presentation.model

/**
 * Common states for any view state: All can load and have errors.
 * This BaseState is implemented by ViewStates used by the ViewModel.
 *
 */
interface ViewState {
    val loadingState: LoadingState
    val errorState: ErrorState?
}
