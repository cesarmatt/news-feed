package com.csr.newsfeed.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csr.newsfeed.data.SourceOption
import com.csr.newsfeed.network.Result
import com.csr.newsfeed.ui.feed.data.FeedUIState
import com.csr.newsfeed.ui.feed.network.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(private val repository: FeedRepository) : ViewModel() {

    private val _source: SourceOption = SourceOption.BBC_NEWS
    val source: SourceOption = _source

    private val _uiState: MutableStateFlow<FeedUIState> = MutableStateFlow(FeedUIState.Idle)
    val uiState: StateFlow<FeedUIState> = _uiState

    init {
        fetchArticles()
    }

    private fun fetchArticles() = viewModelScope.launch {
        _uiState.update { FeedUIState.Loading }
        val response = repository.fetchArticles(_source.queryValue)
        when (response) {
            is Result.Error -> {
                _uiState.update { FeedUIState.Error(response.exception.message.orEmpty()) }
            }
            is Result.Success -> {
                _uiState.update { FeedUIState.Success(response.data) }
            }
        }
    }
}