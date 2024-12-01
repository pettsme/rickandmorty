package com.pettsme.showcase.base.provider.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Used to inject DispatcherProvider into business logic codes, wherever we need to define the dispatcher...
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
}
