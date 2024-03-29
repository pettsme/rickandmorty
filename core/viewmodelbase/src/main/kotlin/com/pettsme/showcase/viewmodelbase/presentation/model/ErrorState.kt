package com.pettsme.showcase.viewmodelbase.presentation.model

/**
 * Modelling different message states, this class is used in the ViewState giving scalable solution
 * to define multiple types of messages on the view.
 */
sealed interface ErrorState {
    data class InlineError(val message: String) : ErrorState
    data class SimpleDialog(val message: String) : ErrorState
    data class Snack(val message: String, val action: (() -> Unit)? = null) : ErrorState
}
