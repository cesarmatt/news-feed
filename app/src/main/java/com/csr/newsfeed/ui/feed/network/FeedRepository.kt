package com.csr.newsfeed.ui.feed.network

class FeedRepository(private val remoteDataSource: FeedRemoteDataSource) {
    suspend fun fetchArticles(sources: String) = remoteDataSource.fetchArticles(sources)
}