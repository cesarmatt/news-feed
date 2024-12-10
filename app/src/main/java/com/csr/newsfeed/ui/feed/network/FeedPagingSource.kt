package com.csr.newsfeed.ui.feed.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.csr.newsfeed.data.Article

class FeedPagingSource(
    private val service: FeedService,
    private val sources: String,
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        } ?: 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val response = service.getArticles(
                page = page,
                sources = sources
            )
            if (response.isSuccessful) {
                val articles = response.body()?.articles ?: emptyList()
                val nextKey = if (articles.isEmpty()) {
                    null
                } else {
                    page + 1
                }
                LoadResult.Page(
                    data = articles,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}