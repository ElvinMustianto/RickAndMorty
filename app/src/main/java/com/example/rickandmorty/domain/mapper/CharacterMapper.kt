package com.example.rickandmorty.domain.mapper

import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.service.response.GetEpisodeByIdResponse
import com.example.rickandmorty.service.response.GetCharacterByIdResponse

object CharacterMapper {

    fun getCharacter(
        response: GetCharacterByIdResponse,
        episodes: List<GetEpisodeByIdResponse> = emptyList()
    ): Character {
        return Character(
            episodesList =episodes.map {
                   EpisodeMapper.buildFrom(it)
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