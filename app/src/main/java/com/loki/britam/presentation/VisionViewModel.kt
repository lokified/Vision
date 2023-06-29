package com.loki.britam.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class VisionViewModel(
    datastore: DataStoreStorage
) : ViewModel() {


    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            block = block
        )
}