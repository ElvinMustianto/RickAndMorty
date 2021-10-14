package com.example.rickandmorty.ui.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import com.example.rickandmorty.Constants
import com.example.rickandmorty.domain.model.Episodes
import com.example.rickandmorty.episode.EpisodePagingSource
import com.example.rickandmorty.episode.EpisodeRepository
import com.example.rickandmorty.episode.EpisodeUiModel
import kotlinx.coroutines.flow.map

class EpisodeViewModel: ViewModel() {

   private val repository = EpisodeRepository()
    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        EpisodePagingSource(repository)
    }.flow.cachedIn(viewModelScope).map {
        it.insertSeparators { model: EpisodeUiModel?, model2: EpisodeUiModel? ->

            // Initial separator list
           if (model == null) {
               return@insertSeparators EpisodeUiModel.Header("Season 1")
           }
           if ( model2 == null) {
               return@insertSeparators null
           }
            // Make sure we only care about the item (episodes)
            if (model is EpisodeUiModel.Header || model2 is EpisodeUiModel.Header) {
                return@insertSeparators null
            }
            // little logic to determine if a separator is necessary
            val episode1 = (model as EpisodeUiModel.Item).episode
            val episode2 = (model2 as EpisodeUiModel.Item).episode
            return@insertSeparators if (episode2.seasonNumber != episode1.seasonNumber) {
                EpisodeUiModel.Header("Season ${episode2.seasonNumber}")
            } else {
                null
            }
        }
    }
}