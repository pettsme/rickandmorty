package com.pettsme.showcase.characterdetails.data

import com.pettsme.showcase.characterdetails.data.model.CharacterDetailsApiModel
import com.pettsme.showcase.characterdetails.data.model.EpisodeApiModel
import com.pettsme.showcase.characterdetails.data.model.FullLocationApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CharacterDetailsApi {

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
    ): Response<CharacterDetailsApiModel>

    @GET("episode/{ids}")
    suspend fun getEpisodes(
        @Path("ids") ids: String,
    ): Response<List<EpisodeApiModel>>

    @GET("location/{id}")
    suspend fun getLocation(
        @Path("id") id: Int,
    ): Response<FullLocationApiModel>
}
