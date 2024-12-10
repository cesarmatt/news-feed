package com.csr.newsfeed.ui.feed.components.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.paging.compose.itemKey
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.csr.newsfeed.data.Article
import kotlinx.coroutines.flow.flowOf

@Composable
fun ArticlesListComponent(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
) {
    when (articles.loadState.refresh) {
        LoadState.Loading -> {
            Text("Loading...")
        }
        is LoadState.Error -> {
            Text("Error")
        }
        is LoadState.NotLoading -> {
            LazyColumn {
                items(
                    count = articles.itemCount,
                    key = articles.itemKey { it.uuid },
                ) { index ->
                    val item = articles[index]
                    if (item != null) {
                        ArticleItemComponent(
                            article = item
                        ) { }
                        Spacer(modifier = modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ArticlesListPreview() {
    ArticlesListComponent(articles = flowOf(PagingData.empty<Article>()).collectAsLazyPagingItems())
}