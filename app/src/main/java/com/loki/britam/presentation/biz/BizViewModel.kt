package com.loki.britam.presentation.biz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BizViewModel: ViewModel() {

    val visibility = mutableStateOf(false)

    fun changeCompanyVisibility(visibility: Boolean) {

    }
}