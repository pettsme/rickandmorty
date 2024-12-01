package com.pettsme.showcase.characters.di

import com.pettsme.showcase.characters.data.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object CharactersModule {
    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharactersApi = retrofit.create()
}
