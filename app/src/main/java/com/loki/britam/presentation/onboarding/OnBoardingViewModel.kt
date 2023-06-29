package com.loki.britam.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.FirebaseAccountRepository
import com.loki.britam.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val datastore: DataStoreStorage,
    private val firebase: FirebaseAccountRepository
): ViewModel(){


    //login state from vision api
    fun getIsLoggedIn(openAndPopUp: (String, String) -> Unit) {

        viewModelScope.launch {
            datastore.getUser().map {
                if(it.isLoggedIn) {
                    openAndPopUp(
                        Screens.HomeScreen.route,
                        Screens.OnBoardingScreen.route
                    )
                }
            }
        }
    }

    //login state from firebase
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        if (firebase.hasUser) {
            openAndPopUp(
                Screens.HomeScreen.route,
                Screens.OnBoardingScreen.route
            )
        }
    }
}