package com.csr.newsfeed.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticlesResponse(val articles: List<Article>): Parcelable
