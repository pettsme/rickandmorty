package com.pettsme.showcase.viewmodelbase.presentation.model

/**
 * Common states for any view state: All can load and have errors.
 * This BaseState is implemented by ViewStates used by the ViewModel.
 *
 */
interface BaseState {
    val isLoading: LoadingState
    val errorState: ErrorState?
}
