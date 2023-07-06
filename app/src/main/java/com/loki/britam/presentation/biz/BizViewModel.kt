package com.loki.britam.presentation.biz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.loki.britam.data.otherCompanies
import com.loki.britam.data.lokiBusiness
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entriesOf

class BizViewModel: ViewModel() {

    val visibility = mutableStateOf(false)

    val comparableModel = mutableStateOf<List<FloatEntry>>(entriesOf(0))

    val myCompany = lokiBusiness

    val otherCompanyList = otherCompanies

    fun setSelectedModel(name: String) {
        for (company in otherCompanyList) {
            if (company.name == name) {
                comparableModel.value = company.data[company.data.size - 1].model
            }
        }
    }

    fun changeCompanyVisibility(visibility: Boolean) {

    }
}