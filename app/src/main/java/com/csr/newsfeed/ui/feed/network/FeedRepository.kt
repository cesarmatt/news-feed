package com.csr.newsfeed.ui.feed.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.csr.newsfeed.data.Article
import kotlinx.coroutines.flow.Flow

class FeedRepository(private val service: FeedService) {

    fun getFeed(sources: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                FeedPagingSource(
                    service = service,
                    sources = sources
                )
            }
        ).flow
    }
}