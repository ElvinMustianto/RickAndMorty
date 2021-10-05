package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.domain.Episode
import com.example.rickandmorty.domain.GetEpisodeByIdResponse

object EpisodeMapper {

    fun getEpisode(networkEpisode: GetEpisodeByIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode
        )
    }
}