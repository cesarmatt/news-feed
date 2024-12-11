package com.csr.newsfeed.ui.detail

import androidx.lifecycle.ViewModel
import com.csr.newsfeed.ui.detail.data.HeadlineDetailViewObject

class HeadlineDetailViewModel(private val _viewObject: HeadlineDetailViewObject) : ViewModel() {
    val viewObject: HeadlineDetailViewObject = _viewObject
}