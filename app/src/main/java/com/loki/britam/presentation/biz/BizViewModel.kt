package com.loki.britam.presentation.biz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.otherCompanies
import com.loki.britam.data.lokiBusiness
import com.loki.britam.data.remote.firebase.Resource
import com.loki.britam.data.remote.firebase.company.CompanyStorage
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.presentation.VisionViewModel
import com.loki.britam.util.DateUtils
import com.loki.britam.util.DateUtils.toYear
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entriesOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BizViewModel @Inject constructor(
    private val dataStore: DataStoreStorage,
    private val storage: CompanyStorage
) : VisionViewModel(dataStore) {

    //company of logged in user
    val companies = storage.companies

    //visibility of data to other app users
    var visibility = mutableStateOf(false)

    val monthSelected = mutableStateOf(DateUtils.getCurrentMonth())
    val yearSelected = mutableStateOf(DateUtils.getCurrentYear().toString())

    //displays on graph
    val comparableModel = mutableStateOf<List<FloatEntry>>(entriesOf(0))
    val myCompany = lokiBusiness
    val otherCompanyList = otherCompanies

    init {
        storage.updateCompanyId(activeCompany.value!!.id)
    }

    fun onMonthSelectedChange(newValue: String) {
        monthSelected.value = newValue
    }

    fun onYearSelectedChange(newValue: String) {
        yearSelected.value = newValue
    }

    fun setSelectedModel(name: String) {
        for (company in otherCompanyList) {
            if (company.name == name) {
                comparableModel.value = company.data[company.data.size - 1].model
            }
        }
    }

    fun changeCompanyVisibility(visible: Boolean) {
        visibility.value = visible
    }

    fun updateActiveCompany(company: Company) {
        viewModelScope.launch {
            dataStore.saveActiveCompany(company)
            storage.updateCompanyId(company.id)
            setExpenseDataDisplay()
        }
    }

    fun setExpenseDataDisplay() {

        resetData()

        launchCatching {

            storage.getCompanyExpense().collect { result ->

                when(result) {
                    is Resource.Success -> {

                        val expenseList = result.data

                        expenseList?.let {
                            expenseData.value = it.find { expense ->
                                    expense.month == monthSelected.value && expense.startDate!!.toYear() == yearSelected.value
                                } ?: Expense()
                        }
                    }
                    is Resource.Loading -> {}

                    is Resource.Error -> {}
                }
            }
        }
    }
}