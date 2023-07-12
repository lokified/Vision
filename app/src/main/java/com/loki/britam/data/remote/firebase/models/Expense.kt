package com.loki.britam.data.remote.firebase.models

import com.google.firebase.firestore.DocumentId
import com.loki.britam.util.DateUtils

data class Expense(
    @DocumentId
    val id: String = "",
    val month: String = DateUtils.getCurrentMonth(),
    val salary: String = "",
    val taxes: String = "",
    val marketing: String = "",
    val rentAndUtilities: String = "",
    val profitLoss: String = "",
    val loss: Boolean = false,
    val startDate: Long? = null,
    val endDate: Long? = null,
    val companyId: String = ""
)
