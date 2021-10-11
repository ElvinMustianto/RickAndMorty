package com.example.rickandmorty.episode

import com.example.rickandmorty.service.NetworkLayer
import com.example.rickandmorty.service.response.GetEpisodesPageResponse

class EpisodeRepository {

    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodesPageResponse? {
        val pageRequest = NetworkLayer.apiClient.getEpisodePage(pageIndex)

        if (!pageRequest.isSuccessful) {
            return null
        }

        return pageRequest.body
    }
}