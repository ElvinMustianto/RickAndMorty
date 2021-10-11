package com.example.rickandmorty.service

import com.example.rickandmorty.service.response.GetEpisodeByIdResponse
import com.example.rickandmorty.service.response.GetCharacterByIdResponse
import com.example.rickandmorty.service.response.GetCharactersPageResponse
import com.example.rickandmorty.service.response.GetEpisodesPageResponse
import retrofit2.Response

class ApiClient (
    private val rickAndMortyService: RickAndMortyService
) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharacterPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }

    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
    }
    suspend fun getEpisodePage(pageIndex: Int): SimpleResponse<GetEpisodesPageResponse> {
        return safeApiCall { rickAndMortyService.getEpisodesPage(pageIndex) }
    }
    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}