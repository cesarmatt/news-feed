package com.csr.newsfeed.ui.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.csr.newsfeed.data.Article
import com.csr.newsfeed.ui.feed.components.headline.FeedHeadlineComponent
import com.csr.newsfeed.ui.feed.components.list.ArticlesListComponent

@Composable
fun FeedScreenHoisting(viewModel: FeedViewModel) {
    val feedItems = viewModel.feedItems.collectAsLazyPagingItems()
    FeedScreen(
        articles = feedItems,
        source = viewModel.source.displayValue
    )
}

@Composable
private fun FeedScreen(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    source: String,
) {
    Scaffold { internalPadding ->
        Surface(modifier = modifier.padding(internalPadding)) {
            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                FeedHeadlineComponent(source = source)
                Spacer(modifier = modifier.height(16.dp))
                ArticlesListComponent(articles = articles)
            }
        }
    }
}