package com.csr.newsfeed.ui.detail.di

import com.csr.newsfeed.ui.detail.HeadlineDetailViewModel
import com.csr.newsfeed.ui.detail.data.HeadlineDetailViewObject
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val headlineDetailModule = module {
    viewModel { (viewObject: HeadlineDetailViewObject) ->
        HeadlineDetailViewModel(viewObject)
    }
}