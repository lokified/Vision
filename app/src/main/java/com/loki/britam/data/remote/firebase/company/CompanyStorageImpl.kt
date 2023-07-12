package com.loki.britam.data.remote.firebase.company

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import com.loki.britam.data.remote.firebase.Resource
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepository
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.data.remote.firebase.models.Investment
import com.loki.britam.data.remote.firebase.trace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CompanyStorageImpl @Inject constructor(
    private val auth: FirebaseAccountRepository,
    private val firestore: FirebaseFirestore
): CompanyStorage {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val companies: Flow<List<Company>>
        get() = auth.currentUser.flatMapLatest { user ->
            firestore.collection(COMPANY_COLLECTION)
                .whereEqualTo(USER_ID_FIELD, user.id)
                .dataObjects()
        }

    override suspend fun getCompanyExpense(): Flow<Resource<List<Expense>?>> =

        callbackFlow {
            trySend(Resource.Loading())

            val expDoc = firestore.collection(COMPANY_DATA_COLLECTION)
                .document(EXPENSES)
                .collection(COMPANY_EXPENSE_COLLECTION)
                .whereEqualTo(COMPANY_ID_FIELD, companyIds.value)
                .addSnapshotListener { snapshot, error ->

                    error?.let { e ->
                        trySend(Resource.Error(e.message.toString()))
                        cancel(e.message.toString())
                    }

                    snapshot?.let {
                        if (!it.isEmpty) {
                            trySend(
                                Resource.Success(
                                    it.toObjects(Expense::class.java)
                                )
                            )
                        }
                    }
                }

            awaitClose { expDoc.remove() }
        }

    override suspend fun getCompanyInvestment(): Flow<Resource<List<Investment>?>>  =

        callbackFlow {
            trySend(Resource.Loading())

            val invDoc = firestore.collection(COMPANY_DATA_COLLECTION)
                .document(INVESTMENT)
                .collection(COMPANY_INVESTMENT_COLLECTION)
                .whereEqualTo(COMPANY_ID_FIELD, companyIds.value)
                .addSnapshotListener { snapshot, error ->

                    error?.let { e ->
                        trySend(Resource.Error(e.message.toString()))
                        cancel(e.message.toString())
                    }

                    snapshot?.let {
                        if (!it.isEmpty) {
                            trySend(
                                Resource.Success(
                                    it.toObjects(Investment::class.java)
                                )
                            )
                        }
                    }
                }

            awaitClose { invDoc.remove() }
        }

    override suspend fun addCompany(company: Company): Unit =
        trace(ADD_COMPANY_TRACE) {
            val companyWithId = company.copy(userId = auth.currentUserId)
            val id = firestore.collection(COMPANY_COLLECTION)
                .add(companyWithId).await().id
            companyIds.value = id
        }

    override suspend fun getCompany(companyId: String): Company? {
        companyIds.value = companyId
        return firestore.collection(COMPANY_COLLECTION)
            .document(companyId).get().await().toObject()
    }

    override suspend fun addCompanyExpense(expense: Expense): Unit =
        trace(ADD_COMPANY_EXPENSE_TRACE) {

            val expenseWithId = expense.copy(companyId = companyIds.value)

            firestore.collection(COMPANY_DATA_COLLECTION)
                .document(EXPENSES)
                .collection(COMPANY_EXPENSE_COLLECTION)
                .add(expenseWithId).await()
        }

    override suspend fun addCompanyInvestment(investment: Investment): Unit =
        trace(ADD_COMPANY_INVESTMENT_TRACE) {

            val investmentWithId = investment.copy(companyId = companyIds.value)

            firestore.collection(COMPANY_DATA_COLLECTION)
                .document(INVESTMENT)
                .collection(COMPANY_INVESTMENT_COLLECTION)
                .add(investmentWithId).await()
        }

    override suspend fun getCompanyExpense(expenseId: String): Expense? {

        expenseIds.value = expenseId

        return firestore.collection(COMPANY_DATA_COLLECTION)
            .document(EXPENSES)
            .collection(COMPANY_EXPENSE_COLLECTION)
            .document(expenseId)
            .get().await().toObject()
    }

    override suspend fun editCompanyExpense(expense: Expense): Unit =
        trace(EDIT_COMPANY_EXPENSE) {
            val expenseWithId = expense.copy(companyId = companyIds.value)
            firestore.collection(COMPANY_DATA_COLLECTION)
                .document(EXPENSES)
                .collection(COMPANY_EXPENSE_COLLECTION)
                .document(expenseIds.value)
                .set(expenseWithId).await()
        }

    override fun updateCompanyId(companyId: String) {
        companyIds.value = companyId
    }

    companion object {

        var companyIds = mutableStateOf("")
        val expenseIds = mutableStateOf("")

        //company values
        const val COMPANY_COLLECTION = "companies"
        const val COMPANY_DATA_COLLECTION = "companiesData"

        //field values
        const val USER_ID_FIELD = "userId"
        const val COMPANY_ID_FIELD = "companyId"

        //company data values
        const val COMPANY_EXPENSE_COLLECTION = "expenses"
        const val EXPENSES = "companiesExpense"
        const val COMPANY_INVESTMENT_COLLECTION = "investments"
        const val INVESTMENT = "companiesInvestments"

        //trace values
        const val EDIT_COMPANY_EXPENSE = "editExpenseTrace"
        const val ADD_COMPANY_TRACE = "addCompanyTrace"
        const val ADD_COMPANY_EXPENSE_TRACE = "addCompanyExpenseTrace"
        const val ADD_COMPANY_INVESTMENT_TRACE = "addCompanyExpenseTrace"
    }
}