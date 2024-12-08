package com.csr.newsfeed.ui.feed.components.headline

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FeedHeadlineComponent(modifier: Modifier = Modifier, source: String) {
    Text(
        modifier = modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.headlineMedium,
        text = source
    )
}

@Preview(showBackground = true)
@Composable
fun FeedHeadlineComponentPreview() {
    FeedHeadlineComponent(source = "Source Name")
}