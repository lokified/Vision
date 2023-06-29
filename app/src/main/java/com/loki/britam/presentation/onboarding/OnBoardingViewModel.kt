package com.loki.britam.presentation.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val datastore: DataStoreStorage
): ViewModel(){


    var isLoggedIn = mutableStateOf(false)

    init {
        getIsLoggedIn()
    }

    private fun getIsLoggedIn() {

        viewModelScope.launch {
            datastore.getUser().map {
                isLoggedIn.value = it.isLoggedIn
            }
        }
    }
}