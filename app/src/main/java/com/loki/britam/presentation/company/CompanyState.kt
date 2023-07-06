package com.loki.britam.presentation.company

import com.loki.britam.data.remote.firebase.models.CompanyData

data class CompanyState(
    val companyData: CompanyData = CompanyData(),
    val isLoading: Boolean = false,
    val error: String = ""
)
