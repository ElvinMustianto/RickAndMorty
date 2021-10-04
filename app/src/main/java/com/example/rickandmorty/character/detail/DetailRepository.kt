package com.example.rickandmorty.character.detail

import com.example.rickandmorty.model.GetCharacterByIdResponse
import com.example.rickandmorty.service.NetworkLayer

class DetailRepository {

    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body
    }
}