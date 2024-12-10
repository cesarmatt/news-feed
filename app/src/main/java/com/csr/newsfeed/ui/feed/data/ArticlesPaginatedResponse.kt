package com.csr.newsfeed.ui.feed.data

import android.os.Parcelable
import com.csr.newsfeed.data.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesPaginatedResponse(
    val articles: List<Article>
): Parcelable
