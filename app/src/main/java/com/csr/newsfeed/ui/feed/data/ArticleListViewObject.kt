package com.csr.newsfeed.ui.feed.data

data class ArticleListViewObject(
    val uuid: String,
    val title: String,
    val date: String,
    val imageUrl: String? = null,
    val url: String? = null,
)
