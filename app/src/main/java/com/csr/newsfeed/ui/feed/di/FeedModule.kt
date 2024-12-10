package com.csr.newsfeed.ui.feed.di

import com.csr.newsfeed.ui.feed.FeedViewModel
import com.csr.newsfeed.ui.feed.network.FeedRepository
import com.csr.newsfeed.ui.feed.network.FeedService
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val feedModule = module {
    factory { get<Retrofit>().create(FeedService::class.java) }
    factory { FeedRepository(get()) }
    viewModel { FeedViewModel(get()) }
}