package com.csr.newsfeed.ui.feed.network

import com.csr.newsfeed.ui.feed.data.ArticlesPaginatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {

    @GET("/v2/top-headlines")
    suspend fun getArticles(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sources") sources: String? = null
    ): Response<ArticlesPaginatedResponse>
}