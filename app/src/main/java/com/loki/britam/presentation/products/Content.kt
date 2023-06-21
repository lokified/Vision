package com.loki.britam.presentation.products

import androidx.annotation.StringRes

data class Content(
    @StringRes
    val header: Int,
    val benefits: List<String>,
    val isPersonal: Boolean = false,
    val url: String = ""
)
