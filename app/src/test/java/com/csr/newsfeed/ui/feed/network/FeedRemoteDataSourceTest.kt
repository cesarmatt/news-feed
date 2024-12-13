@file:OptIn(ExperimentalCoroutinesApi::class)

package com.csr.newsfeed.ui.feed.network

import com.csr.newsfeed.data.Article
import com.csr.newsfeed.data.ArticlesResponse
import com.csr.newsfeed.data.Source
import com.csr.newsfeed.formatter.StringFormatTimestamp
import com.csr.newsfeed.network.DispatcherProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import com.csr.newsfeed.network.Result
import io.mockk.coVerify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import java.io.IOException

class FeedRemoteDataSourceTest {

    private lateinit var feedRemoteDataSource: FeedRemoteDataSource
    private val mockService: FeedService = mockk()
    private val mockDispatchers: DispatcherProvider = mockk()
    private val mockTimestampFormatter: StringFormatTimestamp = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { mockDispatchers.io() } returns testDispatcher
        every { mockDispatchers.default() } returns testDispatcher
        feedRemoteDataSource =
            FeedRemoteDataSource(mockService, mockDispatchers, mockTimestampFormatter)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchArticles returns success when service response is successful`() = runTest {
        val source = Source(
            id = "1",
            name = "test 1"
        )
        val articles = listOf(
            Article(
                uuid = "1",
                title = "Title 1",
                source = source,
                publishedAt = "2023-12-01T10:15:30Z",
                urlToImage = "image1.com",
                url = "url1",
                description = "desc1",
                content = "content1"
            )
        )
        val response = Response.success(ArticlesResponse(articles))
        coEvery {
            mockService.getArticles(page = 0, sources = "source1")
        } returns response
        coEvery { mockTimestampFormatter.formatTimestampToDate(any()) } returns "December 1st, 2023"

        val result = feedRemoteDataSource.fetchArticles("source1")

        assertTrue(result is Result.Success)
        val viewObjects = (result as Result.Success).data
        assertEquals(1, viewObjects.size)
        assertEquals("Title 1", viewObjects[0].title)
        assertEquals("December 1st, 2023", viewObjects[0].date)
        assertEquals("image1.com", viewObjects[0].imageUrl)
        assertEquals("url1", viewObjects[0].url)
        assertEquals("desc1", viewObjects[0].description)
        assertEquals("content1", viewObjects[0].content)

        coVerify(exactly = 1) { mockService.getArticles(page = 0, sources = "source1") }
        coVerify(exactly = 1) { mockTimestampFormatter.formatTimestampToDate("2023-12-01T10:15:30Z") }
    }

    @Test
    fun `fetchArticles returns error when service response is not successful`() = runTest {
        val response = Response.error<ArticlesResponse>(500, mockk(relaxed = true))
        coEvery { mockService.getArticles(page = 0, sources = "source1") } returns response

        val result = feedRemoteDataSource.fetchArticles(sources = "source1")

        assertTrue(result is Result.Error)
        val exception = (result as Result.Error).exception
        assertTrue(exception is IOException)
        assertEquals("An error occurred while fetching articles", exception.message)

        coVerify(exactly = 1) { mockService.getArticles(page = 0, sources = "source1") }
        coVerify(exactly = 0) { mockTimestampFormatter.formatTimestampToDate(any()) }
    }

    @Test
    fun `fetchArticles returns error when service throws exception`() = runTest {
        coEvery { mockService.getArticles(page = 0, sources = "source1") } throws IOException("Service error")

        val result = feedRemoteDataSource.fetchArticles("source1")

        assertTrue(result is Result.Error)
        val exception = (result as Result.Error).exception
        assertTrue(exception is IOException)
        assertEquals("Service error", exception.message)

        coVerify(exactly = 1) { mockService.getArticles(page = 0, sources = "source1") }
        coVerify(exactly = 0) { mockTimestampFormatter.formatTimestampToDate(any()) }
    }

    @Test
    fun `fetchArticles correctly maps articles into view objects`() = runTest {
        val source = Source(
            id = "1",
            name = "test 1"
        )
        val articles = listOf(
            Article(
                uuid = "1",
                title = "Title 1",
                source = source,
                publishedAt = "2023-12-01T10:15:30Z",
                urlToImage = "image1.com",
                url = "url1",
                description = "desc1",
                content = "content1"
            ),
            Article(
                uuid = "2",
                title = "Title 2",
                source = source,
                publishedAt = "2023-12-02T10:15:30Z",
                urlToImage = "image2.com",
                url = "url2",
                description = "desc2",
                content = null
            )
        )
        val response = Response.success(ArticlesResponse(articles))
        coEvery { mockService.getArticles(page = 0, sources = "source1") } returns response
        coEvery { mockTimestampFormatter.formatTimestampToDate("2023-12-01T10:15:30Z") } returns "December 1st, 2023"
        coEvery { mockTimestampFormatter.formatTimestampToDate("2023-12-02T10:15:30Z") } returns "December 2nd, 2023"

        val result = feedRemoteDataSource.fetchArticles("source1")

        assertTrue(result is Result.Success)
        val viewObjects = (result as Result.Success).data
        assertEquals(2, viewObjects.size)

        val article1 = viewObjects[0]
        assertEquals("1", article1.uuid)
        assertEquals("Title 1", article1.title)
        assertEquals("December 1st, 2023", article1.date)
        assertEquals("image1.com", article1.imageUrl)
        assertEquals("url1", article1.url)
        assertEquals("desc1", article1.description)
        assertEquals("content1", article1.content)

        val article2 = viewObjects[1]
        assertEquals("2", article2.uuid)
        assertEquals("Title 2", article2.title)
        assertEquals("December 2nd, 2023", article2.date)
        assertEquals("image2.com", article2.imageUrl)
        assertEquals("url2", article2.url)
        assertEquals("desc2", article2.description)
        assertNull(article2.content)

        coVerify(exactly = 1) { mockService.getArticles(page = 0, sources = "source1") }
        coVerify(exactly = 1) { mockTimestampFormatter.formatTimestampToDate("2023-12-01T10:15:30Z") }
        coVerify(exactly = 1) { mockTimestampFormatter.formatTimestampToDate("2023-12-02T10:15:30Z") }
    }

}