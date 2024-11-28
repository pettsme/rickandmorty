package com.pettsme.showcase.base.di

import com.pettsme.showcase.base.presentation.DefaultStringProvider
import com.pettsme.showcase.base.presentation.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseModule {
    @Binds
    abstract fun bindStringProvider(impl: DefaultStringProvider): StringProvider
}
