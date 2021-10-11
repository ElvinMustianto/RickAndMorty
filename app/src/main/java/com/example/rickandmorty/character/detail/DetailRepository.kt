package com.example.rickandmorty.character.detail

import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.service.response.GetEpisodeByIdResponse
import com.example.rickandmorty.domain.mapper.CharacterMapper
import com.example.rickandmorty.service.Cache
import com.example.rickandmorty.service.response.GetCharacterByIdResponse
import com.example.rickandmorty.service.NetworkLayer

class DetailRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        // Check the cache for our Character
        val cacheCharacter = Cache.characterMap[characterId]
        if (cacheCharacter != null){
            return cacheCharacter
        }
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisode = getEpisodesFromCharacterResponse(request.body)
        val character = CharacterMapper.getCharacter(
            response = request.body,
            episodes = networkEpisode
        )
        // Update cache and return value
        Cache.let {
            Cache.characterMap[characterId] = character
            return character
        }
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