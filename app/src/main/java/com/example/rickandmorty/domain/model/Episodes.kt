package com.example.rickandmorty.domain.model


data class Episodes(
    val id: Int,
    val name: String,
    val airDate: String,
    val episodeNumber: Int,
    val seasonNumber: Int
){
    fun getFormattedSeason(): String {
        return "Season $seasonNumber Episode $episodeNumber"
    }
    fun getFormattedSeasonTruncated(): String {
        return "S $seasonNumber E $episodeNumber"
    }
}