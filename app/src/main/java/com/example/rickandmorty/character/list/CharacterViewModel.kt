package com.example.rickandmorty.character.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rickandmorty.PAGE_SIZE
import com.example.rickandmorty.PREFETCH_DISTANCE
import com.example.rickandmorty.model.GetCharacterByIdResponse

class CharacterViewModel: ViewModel() {

    private val repository = CharacterRepository()
    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharacterDataSourceFactory(viewModelScope, repository)
    val characterPagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()
}