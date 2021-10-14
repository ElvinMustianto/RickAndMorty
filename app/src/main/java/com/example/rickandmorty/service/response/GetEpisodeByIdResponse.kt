package com.example.rickandmorty.service.response

data class GetEpisodeByIdResponse(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String> = listOf(),
    val url: String,
    val created: String
)
