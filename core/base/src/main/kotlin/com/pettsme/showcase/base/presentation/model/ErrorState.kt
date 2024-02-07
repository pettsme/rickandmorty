package com.pettsme.showcase.base.presentation.model

/**
 * Modelling different message states, this class is used in the ViewState giving scalable solution
 * to define multiple types of messages on the view.
 */
sealed class ErrorState {
    data class SimpleDialog(val message: String) : ErrorState()
    data class Snack(val message: String, val action: (() -> Unit)? = null) : ErrorState()
}
