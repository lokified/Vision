package com.loki.britam.data.remote.firebase.company

import com.loki.britam.data.remote.firebase.Resource
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.models.CompanyData
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.data.remote.firebase.models.Investment
import kotlinx.coroutines.flow.Flow

interface CompanyStorage {

    val companies: Flow<List<Company>>

    suspend fun getCompanyExpense(): Flow<Resource<List<Expense>?>>

    suspend fun getCompanyInvestment(): Flow<Resource<List<Investment>?>>

    suspend fun addCompany(company: Company)

    suspend fun getCompany(companyId: String): Company?

    suspend fun addCompanyExpense(expense: Expense)

    suspend fun addCompanyInvestment(investment: Investment)
}