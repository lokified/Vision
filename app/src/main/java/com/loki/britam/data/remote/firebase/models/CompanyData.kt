package com.loki.britam.data.remote.firebase.models

data class CompanyData(
    val expense: List<Expense>? = emptyList(),
    val investment: List<Investment>? = emptyList(),
    val loans: List<Loan>? = emptyList(),
    val insurances: List<Insurance>? = emptyList()
)
