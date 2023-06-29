package com.loki.britam.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class VisionViewModel(
    private val datastore: DataStoreStorage
) : ViewModel() {


    val name = mutableStateOf("")
    val email = mutableStateOf("")

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            datastore.getUser().collect { user ->
                name.value = user.name
                email.value = user.email
            }
        }
    }

    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            block = block
        )
}