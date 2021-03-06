package com.example.rickandmorty.ui.episode

import com.example.rickandmorty.domain.model.Episodes

sealed class EpisodeUiModel {
    class Item(val episode: Episodes): EpisodeUiModel()
    class Header(val text: String): EpisodeUiModel()
}
