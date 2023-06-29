package com.loki.britam.presentation.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.FirebaseAccountRepository
import com.loki.britam.presentation.VisionViewModel
import com.loki.britam.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val datastore: DataStoreStorage,
    private val firebase: FirebaseAccountRepository
): VisionViewModel(datastore) {

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

    fun logOut(openAndStart: (String) -> Unit) {
        launchCatching {
            firebase.signOut()
            openAndStart(Screens.OnBoardingScreen.route)
        }
    }
}