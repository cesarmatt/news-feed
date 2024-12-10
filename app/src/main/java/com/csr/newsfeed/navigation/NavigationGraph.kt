package com.csr.newsfeed.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.csr.newsfeed.ui.feed.FeedScreenHoisting
import com.csr.newsfeed.ui.feed.FeedViewModel
import com.csr.newsfeed.ui.feed.navigation.Feed
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Feed) {
        composable<Feed> {
            val viewModel = koinViewModel<FeedViewModel>()
            FeedScreenHoisting(
                viewModel = viewModel
            )
        }
    }
}