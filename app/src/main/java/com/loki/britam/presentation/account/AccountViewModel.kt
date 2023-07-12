package com.loki.britam.presentation.account

import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.local.datastore.User
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepository
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.presentation.VisionViewModel
import com.loki.britam.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    datastore: DataStoreStorage,
    private val firebase: FirebaseAccountRepository
): VisionViewModel(datastore) {

    fun logOut(openAndStart: (String) -> Unit) {
        launchCatching {
            firebase.signOut()
            updateUser(User())
            setActiveCompany(Company())
            resetData()
            openAndStart(Screens.OnBoardingScreen.route)
        }
    }
}