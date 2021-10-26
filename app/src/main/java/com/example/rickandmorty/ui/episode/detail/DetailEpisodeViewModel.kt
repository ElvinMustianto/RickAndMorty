package com.example.rickandmorty.ui.episode.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.Episodes
import com.example.rickandmorty.ui.episode.EpisodeRepository
import kotlinx.coroutines.launch

class DetailEpisodeViewModel: ViewModel() {

    private val repository = EpisodeRepository()

    private var _episodeLiveData = MutableLiveData<Episodes?>()
    val episodeLiveData: LiveData<Episodes?> = _episodeLiveData

    fun fetchEpisode(episodeId: Int) {
        viewModelScope.launch {
            val episode = repository.getEpisodeById(episodeId)

            _episodeLiveData.postValue(episode)
        }
    }
}