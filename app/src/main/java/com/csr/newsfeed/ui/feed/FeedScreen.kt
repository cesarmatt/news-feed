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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.csr.newsfeed.data.Article
import com.csr.newsfeed.ui.feed.components.headline.FeedHeadlineComponent
import com.csr.newsfeed.ui.feed.components.list.ArticlesListComponent
import com.csr.newsfeed.ui.feed.data.FeedUIState

@Composable
fun FeedScreenHoisting(viewModel: FeedViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    FeedScreen(
        uiState = uiState.value,
        source = viewModel.source.displayValue
    )
}

@Composable
private fun FeedScreen(
    modifier: Modifier = Modifier,
    uiState: FeedUIState,
    source: String,
) {
    Scaffold { internalPadding ->
        Surface(modifier = modifier.padding(internalPadding)) {
            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                FeedHeadlineComponent(source = source)
                Spacer(modifier = modifier.height(16.dp))
                ArticlesListComponent(uiState = uiState)
            }
        }
    }
}