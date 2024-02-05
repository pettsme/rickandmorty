package com.pettsme.showcase.viewmodelbase.presentation.model

sealed class ViewEvent {
    data class Navigate(val target: NavigationTarget) : ViewEvent()
    data class DisplayMessage<T>(val message: T) : ViewEvent()
    data class Effect(val effect: SideEffect) : ViewEvent()
}
