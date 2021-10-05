package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.GetEpisodeByIdResponse
import com.example.rickandmorty.domain.model.GetCharacterByIdResponse

object CharacterMapper {

    fun getCharacter(
        response: GetCharacterByIdResponse,
        episodes: List<GetEpisodeByIdResponse>
    ): Character {
        return Character(
            episodeList = episodes.map {
                 EpisodeMapper.getEpisode(it)
            },
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}