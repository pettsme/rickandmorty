package com.pettsme.showcase.characterlist.di

import com.pettsme.showcase.characterlist.data.CharacterListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object CharacterListModule {
    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharacterListApi = retrofit.create()
}
