package com.example.rickandmorty.service

import com.example.rickandmorty.model.GetCharacterByIdResponse
import com.example.rickandmorty.model.GetCharactersPageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character/")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<GetCharactersPageResponse>
}