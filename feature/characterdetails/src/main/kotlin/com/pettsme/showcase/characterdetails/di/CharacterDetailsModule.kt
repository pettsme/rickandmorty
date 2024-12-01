package com.pettsme.showcase.characterdetails.di

import com.pettsme.showcase.base.provider.dispatcher.DispatcherProvider
import com.pettsme.showcase.characterdetails.data.CharacterDetailsApi
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepository
import com.pettsme.showcase.characterdetails.domain.CharacterDetailsRepositoryMapper
import com.pettsme.showcase.characterdetails.domain.DefaultCharacterDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object CharacterDetailsModule {
    @Provides
    fun providesCharacterDetailsApi(retrofit: Retrofit): CharacterDetailsApi = retrofit.create()

    @Provides
    fun providesCharacterDetailsRepository(
        api: CharacterDetailsApi,
        mapper: CharacterDetailsRepositoryMapper,
        dispatcherProvider: DispatcherProvider,
    ): CharacterDetailsRepository = DefaultCharacterDetailsRepository(
        characterDetailsApi = api,
        mapper = mapper,
        dispatcherProvider = dispatcherProvider,
    )
}
