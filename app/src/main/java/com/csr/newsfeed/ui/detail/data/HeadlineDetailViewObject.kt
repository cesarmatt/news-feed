package com.csr.newsfeed.ui.detail.data

import kotlinx.serialization.Serializable

@Serializable
data class HeadlineDetailViewObject(
    val imageUrl: String?,
    val title: String,
    val description: String,
    val content: String,
    val date: String,
)
