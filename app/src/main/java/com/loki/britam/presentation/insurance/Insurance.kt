package com.loki.britam.presentation.insurance

import androidx.annotation.StringRes

data class Insurance(
    @StringRes
    val content: Int,
    val coverage: List<String>,
    val isPersonal: Boolean = false
)
