package com.csr.newsfeed.ui.feed.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
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
        AsyncImage(
            modifier = modifier.fillMaxHeight().width(80.dp),
            model = article.urlToImage,
            contentScale = ContentScale.Crop,
            contentDescription = "Article image for ${article.title}"
        )
        Spacer(modifier = modifier.width(8.dp))
        Column(
            modifier = modifier.fillMaxHeight().weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = article.title.orEmpty(),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                lineHeight = 18.sp,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.publishedAt.orEmpty(),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Enter ${article.title} content"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview(modifier: Modifier = Modifier) {
    val article = Article(
        title = "Long title to fill the space for new line to see how overflow behaves",
        publishedAt = "December 8th, 2024"
    )
    ArticleItemComponent(
        article = article,
        onClick = {}
    )
}