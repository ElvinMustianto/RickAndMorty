package com.example.rickandmorty.ui.character.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.Constants
import com.example.rickandmorty.Event

class SearchViewModel: ViewModel() {

    private var currentUserSearch: String =""
    private var pagingSource: CharacterSearchPagingSource? = null
    get() {
       if (field == null || field?.invalid == true) {
           field = CharacterSearchPagingSource(currentUserSearch) { localException ->
               Log.e("LOCAL", localException.toString())
           }
       }
        return field
   }
    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(
            pageSize = Constants.PAGE_SIZE,
            prefetchDistance = Constants.PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        pagingSource!!
    }.flow.cachedIn(viewModelScope)

    private val _localExceptionEventLiveData = MutableLiveData<
            Event<CharacterSearchPagingSource.LocalException>>()
    val localExceptionEventLiveData: LiveData<Event<CharacterSearchPagingSource.LocalException>> = _localExceptionEventLiveData

    fun submitQuery(userSearch: String) {
        currentUserSearch = userSearch
        pagingSource?.invalidate()
    }
}