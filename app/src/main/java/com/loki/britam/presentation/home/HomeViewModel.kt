package com.loki.britam.presentation.home

import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.company.CompanyStorage
import com.loki.britam.presentation.VisionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dataStore: DataStoreStorage,
    private val storage: CompanyStorage
): VisionViewModel(dataStore) {


    val companies = storage.companies


}