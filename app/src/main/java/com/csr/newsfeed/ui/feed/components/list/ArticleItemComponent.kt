package com.csr.newsfeed.ui.feed.components.list

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.csr.newsfeed.ui.detail.data.HeadlineDetailViewObject
import com.csr.newsfeed.ui.detail.route.Detail
import com.csr.newsfeed.ui.feed.data.ArticleListViewObject

@Composable
fun ArticleItemComponentHoisting(
    article: ArticleListViewObject,
    onClick: (Detail) -> Unit,
) {
    ArticleItemComponent(
        article = article,
        onClick = onClick
    )
}

@Composable
private fun ArticleItemComponent(
    modifier: Modifier = Modifier,
    article: ArticleListViewObject,
    onClick: (Detail) -> Unit,
) {
    Row(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10)
            )
            .clickable {
                onClick(
                    Detail(
                        imageUrl = article.imageUrl,
                        title = article.title,
                        description = article.description.orEmpty(),
                        content = article.content.orEmpty()
                    )
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxHeight()
                .width(80.dp)
                .padding(8.dp),
            model = article.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Article image for ${article.title}"
        )
        Column(
            modifier = modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                lineHeight = 18.sp,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = article.date,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Icon(
            modifier = modifier.padding(horizontal = 8.dp),
            imageVector = Icons.Default.KeyboardArrowRight,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Enter ${article.title} content"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemPreview(modifier: Modifier = Modifier) {
    val article = ArticleListViewObject(
        title = "Long title to fill the space for new line to see how overflow behaves",
        date = "December 8th, 2024",
        uuid = "1234567890"
    )
    ArticleItemComponent(
        article = article,
        onClick = {}
    )
}