package com.csr.newsfeed.ui.feed.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FeedHeadlineComponent(source: String) {
    Text(
        style = MaterialTheme.typography.headlineMedium,
        text = source
    )
}

@Preview(showBackground = true)
@Composable
fun FeedHeadlineComponentPreview() {
    FeedHeadlineComponent(source = "Source Name")
}