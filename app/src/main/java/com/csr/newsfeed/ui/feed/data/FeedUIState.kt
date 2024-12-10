package com.csr.newsfeed.ui.feed.data

sealed interface FeedUIState {
    data object Idle : FeedUIState
    data object Loading : FeedUIState
    data class Success(val articles: List<ArticleListViewObject>) : FeedUIState
    data class Error(val message: String) : FeedUIState
}