package com.example.rickandmorty.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.detail.DetailRepository
import com.example.rickandmorty.domain.model.Character
import com.example.rickandmorty.service.Cache
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val repository = DetailRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData: LiveData<Character?> = _characterByIdLiveData

    fun fetchCharacter(characterId: Int) {
        // We need to make network to call for the character
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}