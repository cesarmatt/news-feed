package com.csr.newsfeed.ui.feed.components.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csr.newsfeed.data.Article

@Composable
fun ArticlesListComponent(modifier: Modifier = Modifier) {
    val article = Article(
        title = "Title",
        publishedAt = "December 8th, 2024"
    )
    LazyColumn {
        items(10) {
            ArticleItemComponent(article = article) {

            }
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun ArticlesListPreview() {
    ArticlesListComponent()
}