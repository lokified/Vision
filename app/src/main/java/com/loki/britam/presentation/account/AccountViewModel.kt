package com.loki.britam.presentation.account

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepository
import com.loki.britam.presentation.VisionViewModel
import com.loki.britam.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val datastore: DataStoreStorage,
    private val firebase: FirebaseAccountRepository
): VisionViewModel(datastore) {

    fun logOut(openAndStart: (String) -> Unit) {
        launchCatching {
            firebase.signOut()
            openAndStart(Screens.OnBoardingScreen.route)
        }
    }
}