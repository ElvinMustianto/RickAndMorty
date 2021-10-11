package com.example.rickandmorty.character.list

import com.example.rickandmorty.service.response.GetCharactersPageResponse
import com.example.rickandmorty.service.NetworkLayer

class CharacterRepository {
    suspend fun getCharacterPage(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharacterPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body
    }
}