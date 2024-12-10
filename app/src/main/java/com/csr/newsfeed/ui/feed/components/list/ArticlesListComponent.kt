package com.csr.newsfeed.ui.feed.components.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csr.newsfeed.ui.feed.data.FeedUIState

@Composable
fun ArticlesListComponent(
    modifier: Modifier = Modifier,
    uiState: FeedUIState
) {
    when (uiState) {
        FeedUIState.Loading -> {}
        is FeedUIState.Error -> {}
        is FeedUIState.Success -> {
            val articles = uiState.articles
            LazyColumn {
                items(items = articles, key = { it.uuid }) {
                    ArticleItemComponentHoisting(
                        article = it
                    ) { }
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
        }
        else -> return
    }
}

@Preview
@Composable
private fun ArticlesListPreview() {
    ArticlesListComponent(uiState = FeedUIState.Idle)
}