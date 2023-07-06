package com.loki.britam.presentation.company

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.google.firebase.firestore.FirebaseFirestoreException
import com.loki.britam.core.Constants.COMPANY_ID
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.Resource
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.company.CompanyStorage
import com.loki.britam.data.remote.firebase.models.CompanyData
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.presentation.VisionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    dataStore: DataStoreStorage,
    savedStateHandle: SavedStateHandle,
    private val storage: CompanyStorage
): VisionViewModel(dataStore) {

    val newExpenseFormState = FormState(
        fields = listOf(

            TextFieldState(
                name = "Salary",
                validators = listOf()
            ),
            TextFieldState(
                name = "Taxes",
                validators = listOf()
            ),
            TextFieldState(
                name = "Marketing",
                validators = listOf()
            ),
            TextFieldState(
                name = "Profit",
                validators = listOf(
                    Validators.Required()
                )
            ),
        )
    )

    private val _data = MutableStateFlow(CompanyState())
    val data = _data.asStateFlow()

    var company = mutableStateOf<Company?>(null)

    init {
        savedStateHandle.get<String>(COMPANY_ID)?.let { id ->
            getCompanyDetails(id)
        }
        getCompanyData()
    }

    private fun getCompanyDetails(id: String) {
        launchCatching {

            try {
                isLoading.value = true
                company.value = storage.getCompany(id)
                isLoading.value = false
            }
            catch (e: FirebaseFirestoreException) {
                isLoading.value = false
                errorMessage.value = e.message ?: "something went wrong"
            }
        }
    }

    fun addCompanyExpense(expense: Expense, popScreen: () -> Unit) {

        launchCatching {

            try {
                isLoading.value = true
                storage.addCompanyExpense(
                    expense = Expense(
                        month = expense.month,
                        isProfit = expense.isProfit,
                        taxes = expense.taxes,
                        marketing = expense.marketing,
                        salary = expense.salary,
                        profitLoss = expense.profitLoss
                    )
                )
                isLoading.value = false
                popScreen()
            }
            catch (e: FirebaseFirestoreException) {
                isLoading.value = false
                errorMessage.value = e.message ?: "something went wrong"
            }
        }
    }

    private fun getCompanyData() {
        launchCatching {

            storage.getCompanyExpense().collect { result ->

                when(result) {
                    is Resource.Loading -> {
                        _data.value = CompanyState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _data.value = CompanyState(
                            companyData = CompanyData(
                                expense = result.data
                            )
                        )
                    }

                    is Resource.Error -> {
                        _data.value = CompanyState(
                            error = result.message
                        )
                    }
                }
            }
        }
    }
}