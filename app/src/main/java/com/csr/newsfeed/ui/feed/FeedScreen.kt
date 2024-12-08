package com.csr.newsfeed.ui.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.csr.newsfeed.ui.feed.components.headline.FeedHeadlineComponent
import com.csr.newsfeed.ui.feed.components.list.ArticlesListComponent

@Composable
fun FeedScreenHoisting() {
    FeedScreen()
}

@Composable
private fun FeedScreen(modifier: Modifier = Modifier) {
    Scaffold { internalPadding ->
        Surface(modifier = modifier.padding(internalPadding)) {
            Column(modifier = modifier.padding(horizontal = 16.dp)) {
                FeedHeadlineComponent(source = "Source xD")
                Spacer(modifier = modifier.height(16.dp))
                ArticlesListComponent()
            }
        }
    }
}