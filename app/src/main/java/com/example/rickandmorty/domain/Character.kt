package com.example.rickandmorty.domain

import com.example.rickandmorty.domain.model.Location
import com.example.rickandmorty.domain.model.Origin
import com.squareup.moshi.Json

data class Character(
    val episodeList: List<Episode> = listOf(),
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
