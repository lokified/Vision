package com.loki.britam.presentation.home

import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    val companies = mutableListOf("Loki")

    fun addCompany(name: String) {
        companies.add(name)
    }
}