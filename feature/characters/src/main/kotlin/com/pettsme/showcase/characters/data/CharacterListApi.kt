package com.pettsme.showcase.characters.data

import com.pettsme.showcase.characters.data.model.CharactersApiModel
import com.pettsme.showcase.network.data.QUERY_PARAM_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CharacterListApi {

    @GET("character")
    suspend fun getCharacters(
        @Query(QUERY_PARAM_PAGE) page: Int,
    ): Response<CharactersApiModel>
}
