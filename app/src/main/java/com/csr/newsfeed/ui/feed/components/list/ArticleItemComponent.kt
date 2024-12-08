package com.csr.newsfeed.ui.feed.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csr.newsfeed.data.Article

@Composable
fun ArticleItemComponent(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .clickable { onClick(article.url.orEmpty()) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .width(80.dp)
                .background(Color.Blue)
        )
        Spacer(modifier = modifier.width(8.dp))
        Column(
            modifier = modifier.fillMaxHeight().weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = article.title.orEmpty(),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = article.publishedAt.orEmpty(),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Enter ${article.title} content"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview(modifier: Modifier = Modifier) {
    val article = Article(
        title = "Title",
        publishedAt = "December 8th, 2024"
    )
    ArticleItemComponent(
        article = article,
        onClick = {}
    )
}