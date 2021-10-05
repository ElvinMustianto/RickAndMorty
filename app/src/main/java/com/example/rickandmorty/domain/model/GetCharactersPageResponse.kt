package com.example.rickandmorty.domain.model


data class GetCharactersPageResponse(
    val info: Info = Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
)