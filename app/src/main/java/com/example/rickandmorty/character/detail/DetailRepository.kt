package com.example.rickandmorty.character.detail

import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.GetEpisodeByIdResponse
import com.example.rickandmorty.domain.mapper.CharacterMapper
import com.example.rickandmorty.domain.model.GetCharacterByIdResponse
import com.example.rickandmorty.service.NetworkLayer

class DetailRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisode = getEpisodesFromCharacterResponse(request.body)
        return CharacterMapper.getCharacter(
            response = request.body,
            episodes = networkEpisode
        )
    }

    private suspend fun getEpisodesFromCharacterResponse(
        body: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = body.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()

        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }
        return request.body
    }
}