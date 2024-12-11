package com.csr.newsfeed

import android.app.Application
import com.csr.newsfeed.di.networkModule
import com.csr.newsfeed.ui.detail.di.headlineDetailModule
import com.csr.newsfeed.ui.feed.di.feedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class NewsFeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NewsFeedApplication)
            modules(
                networkModule,
                feedModule,
                headlineDetailModule
            )
        }
    }
}