package com.example.rickandmorty.ui.episode

import com.example.rickandmorty.domain.mapper.EpisodeMapper
import com.example.rickandmorty.domain.model.Episodes
import com.example.rickandmorty.service.NetworkLayer
import com.example.rickandmorty.service.response.GetCharacterByIdResponse
import com.example.rickandmorty.service.response.GetEpisodeByIdResponse
import com.example.rickandmorty.service.response.GetEpisodesPageResponse

class EpisodeRepository {

    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodesPageResponse? {
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)

        if (!pageRequest.isSuccessful) {
            return null
        }

        return pageRequest.body
    }
    suspend fun getEpisodeById(episodeId: Int): Episodes? {
        val request = NetworkLayer.apiClient.getEpisodeById(episodeId)

        if (!request.isSuccessful) {
            return null
        }
        val characterList = getCharacterFromEpisodeResponse(request.body)
        return EpisodeMapper.buildFrom(
            networkEpisode = request.body,
            networkCharacters = characterList
        )
    }
    private suspend fun getCharacterFromEpisodeResponse(
        episodeByIdResponse: GetEpisodeByIdResponse
    ): List<GetCharacterByIdResponse> {
        val characterList = episodeByIdResponse.characters.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }
        val request = NetworkLayer.apiClient.getMultipleCharacters(characterList)
        return request.bodyNullable ?: emptyList()
    }
}