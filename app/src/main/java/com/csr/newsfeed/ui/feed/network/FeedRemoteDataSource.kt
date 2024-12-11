package com.csr.newsfeed.ui.feed.network

import com.csr.newsfeed.data.Article
import com.csr.newsfeed.ext.formatTimestampToDate
import com.csr.newsfeed.network.DefaultDispatcherProvider
import com.csr.newsfeed.network.DispatcherProvider
import kotlinx.coroutines.withContext
import com.csr.newsfeed.network.Result
import com.csr.newsfeed.ui.feed.data.ArticleListViewObject
import okio.IOException

class FeedRemoteDataSource(
    private val service: FeedService,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) {

    suspend fun fetchArticles(sources: String): Result<List<ArticleListViewObject>> = withContext(dispatchers.io()) {
        try {
            val response = service.getArticles(sources = sources)
            if (response.isSuccessful) {
                val articles = response.body()?.articles.orEmpty()
                val viewObject = makeArticleViewObjectFromArticles(articles)
                return@withContext Result.Success(viewObject)
            } else {
                return@withContext Result.Error(
                    IOException("An error occurred while fetching articles")
                )
            }
        } catch (exception: Exception) {
            return@withContext Result.Error(exception)
        }
    }

    private suspend fun makeArticleViewObjectFromArticles(articles: List<Article>): List<ArticleListViewObject> = withContext(dispatchers.default()) {
        return@withContext articles.map {
            ArticleListViewObject(
                uuid = it.uuid,
                title = it.title.orEmpty(),
                date = it.publishedAt?.formatTimestampToDate().orEmpty(),
                imageUrl = it.urlToImage,
                url = it.url,
                description = it.description,
                content = it.content?.substringBefore("[")
            )
        }
    }
}