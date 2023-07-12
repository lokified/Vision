package com.loki.britam.presentation.company

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.firestore.FirebaseFirestoreException
import com.loki.britam.core.Constants.COMPANY_ID
import com.loki.britam.core.Constants.EXPENSE_DEFAULT_ID
import com.loki.britam.core.Constants.EXPENSE_ID
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

    private val _data = MutableStateFlow(CompanyState())
    val data = _data.asStateFlow()

    var company = mutableStateOf<Company?>(null)

    private val _expense = mutableStateOf(Expense())
    val expense = _expense

    var isEditScreen = mutableStateOf(false)

    init {

        savedStateHandle.get<String>(COMPANY_ID)?.let { companyId ->
            getCompanyDetails(companyId)
        }

        savedStateHandle.get<String>(EXPENSE_ID)?.let { expenseId ->
            launchCatching {
                if (expenseId != EXPENSE_DEFAULT_ID) {
                    _expense.value = storage.getCompanyExpense(expenseId) ?: Expense()
                    isEditScreen.value = true
                }
            }
        }

        getCompanyData()
    }


    fun onSalaryFieldChange(newValue: String) {
        _expense.value = _expense.value.copy(salary = newValue)
    }

    fun onTaxFieldChange(newValue: String) {
        _expense.value = _expense.value.copy(taxes = newValue)
    }

    fun onMarketingFieldChange(newValue: String) {
        _expense.value = _expense.value.copy(marketing = newValue)
    }

    fun onUtilitiesFieldChange(newValue: String) {
        _expense.value = _expense.value.copy(rentAndUtilities = newValue)
    }

    fun onProfitLossFieldChange(newValue: String) {
        _expense.value = _expense.value.copy(profitLoss = newValue)
    }

    fun onMonthChange(newValue: String) {
        _expense.value = _expense.value.copy(month = newValue)
    }

    fun onStartDateChange(newValue: Long) {
        _expense.value = _expense.value.copy(startDate = newValue)
    }

    fun onEndDateChange(newValue: Long) {
        _expense.value = _expense.value.copy(endDate = newValue)
    }

    fun onIsProfitLossChange(newValue: Boolean) {
        _expense.value = _expense.value.copy(loss = newValue)
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

    fun addCompanyExpense(popScreen: () -> Unit) {

        launchCatching {

            try {
                isLoading.value = true

                if (!isEditScreen.value) {
                    storage.addCompanyExpense(
                        expense = _expense.value
                    )
                } else {
                    storage.editCompanyExpense(
                        expense = _expense.value
                    )
                }

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