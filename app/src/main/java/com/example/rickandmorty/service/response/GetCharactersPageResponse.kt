package com.example.rickandmorty.service.response

data class GetCharactersPageResponse(
    val info: PageInfo = PageInfo(),
    val results: List<GetCharacterByIdResponse> = emptyList()
)