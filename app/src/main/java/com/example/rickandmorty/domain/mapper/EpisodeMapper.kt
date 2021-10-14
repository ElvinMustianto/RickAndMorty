package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.domain.model.Episodes
import com.example.rickandmorty.service.response.GetCharacterByIdResponse
import com.example.rickandmorty.service.response.GetEpisodeByIdResponse


object EpisodeMapper {
    fun buildFrom(
        networkEpisode: GetEpisodeByIdResponse,
        networkCharacters: List<GetCharacterByIdResponse> = emptyList()
        ): Episodes {
        return Episodes(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            seasonNumber = getSeasonFromEpisodeString(networkEpisode.episode),
            episodeNumber = getEpisodeFromEpisodeString(networkEpisode.episode),
            characters = networkCharacters.map {
                CharacterMapper.getCharacter(it)
            }
        )
    }
    private fun getSeasonFromEpisodeString(episode: String): Int {
        val endIndex = episode.indexOfFirst { it.equals('e', true) }
        if (endIndex == -1) {
            return 0
        }
        return episode.substring(1, endIndex).toIntOrNull() ?: 0
    }
    private fun getEpisodeFromEpisodeString(episode: String): Int {
        val startIndex = episode.indexOfFirst { it.equals('e', true) }
        if (startIndex == -1) {
            return 0
        }
        return episode.substring(startIndex + 1).toIntOrNull() ?: 0
    }
}