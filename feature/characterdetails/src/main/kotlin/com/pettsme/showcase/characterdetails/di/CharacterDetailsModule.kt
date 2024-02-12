package com.pettsme.showcase.characterdetails.di

import com.pettsme.showcase.characterdetails.data.CharacterDetailsApi
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
    fun providesCharacterApi(retrofit: Retrofit): CharacterDetailsApi = retrofit.create()
}
