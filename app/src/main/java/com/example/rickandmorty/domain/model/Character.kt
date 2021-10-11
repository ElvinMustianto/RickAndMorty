package com.example.rickandmorty.domain.model

data class Character(
    val episodesList: List<Episodes> = listOf(),
    val gender: String,
    val id: Int = 0,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
) {
    data class Location(
        val name: String,
        val url: String
    )
    data class Origin(
        val name: String,
        val url: String
    )

}
