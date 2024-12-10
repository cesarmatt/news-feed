package com.csr.newsfeed.ext

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatTimestampToDate(): String {
    val zonedDateTime = ZonedDateTime.parse(this)
    val dayOfMonth = zonedDateTime.dayOfMonth
    val suffix = when {
        dayOfMonth in 11..13 -> "th"
        dayOfMonth % 10 == 1 -> "st"
        dayOfMonth % 10 == 2 -> "nd"
        dayOfMonth % 10 == 3 -> "rd"
        else -> "th"
    }
    val formatter = DateTimeFormatter.ofPattern("MMMM d'$suffix', yyyy", Locale.ENGLISH)
    return zonedDateTime.format(formatter)
}