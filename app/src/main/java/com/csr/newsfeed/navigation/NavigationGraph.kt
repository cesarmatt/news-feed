package com.csr.newsfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.csr.newsfeed.ui.detail.HeadlineDetailScreenHoisting
import com.csr.newsfeed.ui.detail.HeadlineDetailViewModel
import com.csr.newsfeed.ui.detail.data.HeadlineDetailViewObject
import com.csr.newsfeed.ui.detail.route.Detail
import com.csr.newsfeed.ui.feed.FeedScreenHoisting
import com.csr.newsfeed.ui.feed.FeedViewModel
import com.csr.newsfeed.ui.feed.route.Feed
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Feed) {
        composable<Feed> {
            val viewModel = koinViewModel<FeedViewModel>()
            FeedScreenHoisting(
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable<Detail> {
            val args = it.toRoute<Detail>()
            val viewModel = koinViewModel<HeadlineDetailViewModel> {
                parametersOf(
                    HeadlineDetailViewObject(
                        imageUrl = args.imageUrl,
                        title = args.title,
                        description = args.description,
                        content = args.content,
                    )
                )
            }
            HeadlineDetailScreenHoisting(
                viewModel = viewModel,
                navController = navHostController
            )
        }
    }
}