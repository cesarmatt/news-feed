package com.csr.newsfeed.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.csr.newsfeed.ui.detail.data.HeadlineDetailViewObject

@Composable
fun HeadlineDetailScreenHoisting(
    viewModel: HeadlineDetailViewModel,
    navController: NavController,
) {
    HeadlineDetailScreen(
        viewObject = viewModel.viewObject,
        onNavigationClicked = {
            navController.popBackStack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HeadlineDetailScreen(
    modifier: Modifier = Modifier,
    viewObject: HeadlineDetailViewObject,
    onNavigationClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigationClicked() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate to previous screen",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { internalPadding ->
        Surface(modifier = modifier.padding(internalPadding)) {
            Column(modifier = modifier.padding(16.dp)) {
                Text(
                    text = viewObject.title,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = viewObject.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = viewObject.date,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = modifier.height(8.dp))
                AsyncImage(model = viewObject.imageUrl, contentDescription = null)
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = viewObject.content,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HeadlineDetailScreenPreview() {
    val viewObject = HeadlineDetailViewObject(
        imageUrl = "https://m.ahstatic.com/is/image/accorhotels/eventos-em-porto-alegre-2024-1:3by2?fmt=jpg&op_usm=1.75,0.3,2,0&resMode=sharp2&iccEmbed=true&icc=sRGB&dpr=on,1.5&wid=335&hei=223&qlt=80",
        title = "Porto Alegre - best city in the world",
        description = "The capital of Rio Grande do Sul is the best city ever",
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mi augue, consequat sed libero sit amet, maximus consectetur arcu. Phasellus tincidunt volutpat eros, sit amet condimentum nisl interdum vel. Nullam sit amet metus in metus sagittis pulvinar. Integer mauris lorem, efficitur vitae elit ac, gravida tristique purus. Ut maximus consequat odio in aliquam. Vivamus semper, erat sed vestibulum efficitur, eros nibh commodo enim, ut vulputate massa nisi nec metus. Sed venenatis aliquam congue.",
        date = "December 11th, 2024"
    )
    HeadlineDetailScreen(viewObject = viewObject) { }
}