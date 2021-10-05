package com.example.rickandmorty.character.list

import androidx.paging.DataSource
import com.example.rickandmorty.domain.model.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class CharacterDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharacterRepository
): DataSource.Factory<Int, GetCharacterByIdResponse>() {

    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }
}