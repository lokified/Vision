package com.loki.britam.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.local.datastore.User
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.models.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class VisionViewModel(
    private val datastore: DataStoreStorage
) : ViewModel() {

    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val userId = mutableStateOf("")

    val activeCompany = mutableStateOf<Company?>(null)

    //expense data of active company
    val expenseData = mutableStateOf(Expense())

    init {
        getUser()
        getActiveCompany()
    }

    private fun getUser() {
        viewModelScope.launch {
            datastore.getUser().collect { user ->
                userId.value = user.userId
                name.value = user.name
                email.value = user.email
            }
        }
    }

    fun updateUser(user: User) {

        viewModelScope.launch {
            datastore.saveUser(
                User(
                    userId = user.userId,
                    name = user.name,
                    email = user.email,
                    isLoggedIn = user.isLoggedIn
                )
            )
        }
    }

    private fun getActiveCompany() {
        viewModelScope.launch {
            datastore.getActiveCompany().collect {
                activeCompany.value = it
            }
        }
    }

    fun setActiveCompany(company: Company) {
        viewModelScope.launch {
            datastore.saveActiveCompany(company)
        }
    }

    fun launchCatching(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            block = block
        )

    fun resetData() {
        expenseData.value = Expense()
    }
}