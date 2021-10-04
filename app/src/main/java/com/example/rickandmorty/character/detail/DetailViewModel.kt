package com.example.rickandmorty.character.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val repository = DetailRepository()

    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterByIdLiveData: LiveData<GetCharacterByIdResponse?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val character = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(character)
        }
    }
}