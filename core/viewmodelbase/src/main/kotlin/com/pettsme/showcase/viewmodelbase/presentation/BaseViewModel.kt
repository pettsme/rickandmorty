package com.pettsme.showcase.viewmodelbase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pettsme.showcase.base.DispatcherProvider
import com.pettsme.showcase.core.domain.model.base.RepositoryError
import com.pettsme.showcase.viewmodelbase.presentation.model.ViewEvent
import com.pettsme.showcase.viewmodelbase.presentation.model.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow.SUSPEND
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * This is the common part of all ViewModels, dealing with ViewState and SideEffect propagation.
 */
abstract class BaseViewModel<VS : ViewState, Action>(
    initialState: VS,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _state: MutableStateFlow<VS> = MutableStateFlow(initialState)
    val state: StateFlow<VS> = _state.asStateFlow()

    private val _events = Channel<ViewEvent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = SUSPEND,
        onUndeliveredElement = {
            Timber.e(IllegalStateException("Missed view event $it"))
        },
    )
    val events
        get() = _events.receiveAsFlow()

    protected val currentState: VS
        get() = state.value

    abstract fun onViewAction(viewAction: Action)

    abstract fun handleError(throwable: RepositoryError)

    protected fun dispatchViewEvent(event: ViewEvent) {
        launch {
            _events.send(event)
        }
    }

    protected fun launch(
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(context = dispatcherProvider.main, block = block)
    }

    /**
     * Updates the [currentState] with the value returned from the [transform] lambda
     */
    protected fun updateState(transform: (VS) -> VS) {
        _state.update(transform)
    }
}
