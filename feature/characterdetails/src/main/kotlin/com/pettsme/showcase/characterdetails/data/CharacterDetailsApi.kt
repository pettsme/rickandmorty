package com.pettsme.showcase.characterdetails.data

import com.pettsme.showcase.characterdetails.data.model.CharacterDetailsApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CharacterDetailsApi {

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): Response<CharacterDetailsApiModel>
}
