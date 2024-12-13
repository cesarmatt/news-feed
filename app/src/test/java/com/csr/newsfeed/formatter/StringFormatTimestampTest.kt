package com.csr.newsfeed.formatter

import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime


class StringFormatTimestampTest {

    private lateinit var stringFormatTimestamp: StringFormatTimestamp

    @Before
    fun setup() {
        stringFormatTimestamp = StringFormatTimestamp()
    }

    @Test
    fun `formatTimestampToDate returns correctly formatted date with st suffix`() {
        val timestamp = "2023-12-01T10:15:30Z"
        val expected = "December 1st, 2023"
        val result = stringFormatTimestamp.formatTimestampToDate(timestamp)
        assertEquals(expected, result)
    }

    @Test
    fun `formatTimestampToDate returns correctly formatted date with nd suffix`() {
        val timestamp = "2023-12-02T10:15:30Z"
        val expected = "December 2nd, 2023"
        val result = stringFormatTimestamp.formatTimestampToDate(timestamp)
        assertEquals(expected, result)
    }

    @Test
    fun `formatTimestampToDate returns correctly formatted date with rd suffix`() {
        val timestamp = "2023-12-03T10:15:30Z"
        val expected = "December 3rd, 2023"
        val result = stringFormatTimestamp.formatTimestampToDate(timestamp)
        assertEquals(expected, result)
    }

    @Test
    fun `formatTimestampToDate returns correctly formatted date with th suffix`() {
        val timestamp = "2023-12-04T10:15:30Z"
        val expected = "December 4th, 2023"
        val result = stringFormatTimestamp.formatTimestampToDate(timestamp)
        assertEquals(expected, result)
    }

    @Test
    fun `formatTimestampToDate handles 11th to 13th day edge case`() {
        val testCases = mapOf(
            "2023-12-11T10:15:30Z" to "December 11th, 2023",
            "2023-12-12T10:15:30Z" to "December 12th, 2023",
            "2023-12-13T10:15:30Z" to "December 13th, 2023"
        )
        for ((timestamp, expected) in testCases) {
            val result = stringFormatTimestamp.formatTimestampToDate(timestamp)
            assertEquals(expected, result)
        }
    }

    @Test(expected = Exception::class)
    fun `formatTimestampToDate throws exception for invalid timestamp`() {
        val invalidTimestamp = "invalid-timestamp"
        stringFormatTimestamp.formatTimestampToDate(invalidTimestamp)
    }
}