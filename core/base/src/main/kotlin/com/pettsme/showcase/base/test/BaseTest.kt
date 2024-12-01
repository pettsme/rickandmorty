package com.pettsme.showcase.base.test

import com.pettsme.showcase.base.provider.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseTest<T> {

    private val scope = TestScope()
    private val testDispatcher = StandardTestDispatcher(scope.testScheduler)
    protected val testDispatcherProvider: DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher = testDispatcher
        override val default: CoroutineDispatcher = testDispatcher
        override val io: CoroutineDispatcher = testDispatcher
    }

    abstract var underTest: T

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher = testDispatcherProvider.main)
    }

    @AfterTest
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    fun startTest(testBlock: suspend TestScope.() -> Unit) = scope.runTest(testBody = testBlock)
}
