package com.example.rickandmorty.domain

data class GetEpisodeByIdResponse(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val character: List<String> = listOf(),
    val url: String,
    val created: String
)
