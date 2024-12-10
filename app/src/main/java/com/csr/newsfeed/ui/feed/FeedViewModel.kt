package com.csr.newsfeed.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.csr.newsfeed.data.Article
import com.csr.newsfeed.data.SourceOption
import com.csr.newsfeed.ui.feed.network.FeedRepository
import kotlinx.coroutines.flow.Flow

class FeedViewModel(private val repository: FeedRepository) : ViewModel() {

    private val _source: SourceOption = SourceOption.BBC_NEWS
    val source: SourceOption = _source

    val feedItems: Flow<PagingData<Article>> = repository.getFeed(source.queryValue).cachedIn(viewModelScope)
}