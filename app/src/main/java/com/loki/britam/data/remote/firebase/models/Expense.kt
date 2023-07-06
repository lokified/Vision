package com.loki.britam.data.remote.firebase.models

import com.google.firebase.firestore.DocumentId

data class Expense(
    @DocumentId
    val id: String = "",
    val month: String = "",
    val salary: String = "",
    val taxes: String = "",
    val marketing: String = "",
    val profitLoss: String = "",
    val isProfit: Boolean = false,
    val companyId: String = ""
)
