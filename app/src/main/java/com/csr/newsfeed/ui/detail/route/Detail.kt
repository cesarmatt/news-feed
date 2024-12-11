package com.csr.newsfeed.ui.detail.route

import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    val imageUrl: String?,
    val title: String,
    val description: String,
    val content: String
)