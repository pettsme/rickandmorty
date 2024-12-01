package com.pettsme.showcase.base.di

import com.pettsme.showcase.base.presentation.DefaultStringProvider
import com.pettsme.showcase.base.presentation.StringProvider
import com.pettsme.showcase.base.provider.dispatcher.DefaultDispatcherProvider
import com.pettsme.showcase.base.provider.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseModule {
    @Binds
    abstract fun bindStringProvider(impl: DefaultStringProvider): StringProvider

    @Binds
    abstract fun bindsDispatcherProvider(impl: DefaultDispatcherProvider): DispatcherProvider
}
