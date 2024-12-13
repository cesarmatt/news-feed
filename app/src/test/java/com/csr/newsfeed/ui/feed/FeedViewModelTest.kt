package com.csr.newsfeed.ui.feed

import com.csr.newsfeed.ui.feed.data.ArticleListViewObject
import com.csr.newsfeed.ui.feed.network.FeedRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.csr.newsfeed.network.Result
import com.csr.newsfeed.ui.feed.data.FeedUIState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class FeedViewModelTest {

    private lateinit var viewModel: FeedViewModel
    private val repository: FeedRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState should be success when repository call succeeds`() = runTest {
        val mockArticles = listOf(
            ArticleListViewObject(
                uuid = "1",
                title = "Breaking News",
                date = "December 11th, 2023",
                imageUrl = "https://example.com/image1.jpg",
                url = "https://example.com/article1",
                content = "This is the content of the first article.",
                description = "This is the description of the first article."
            )
        )
        coEvery { repository.fetchArticles("bbc-news") } returns Result.Success(mockArticles)
        viewModel = FeedViewModel(repository)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(FeedUIState.Success(mockArticles), uiState)
    }

    @Test
    fun `uiState is Loading then Error when fetchArticles fails`() = runTest {
        coEvery { repository.fetchArticles("bbc-news") } returns Result.Error(IOException("Error fetching articles"))
        viewModel = FeedViewModel(repository)
        advanceUntilIdle()

        val uiState = viewModel.uiState.value
        assertEquals(FeedUIState.Error::class.java, uiState::class.java)
        val uiStateAsError = uiState as FeedUIState.Error
        assertEquals("Error fetching articles", uiStateAsError.message)
    }
}