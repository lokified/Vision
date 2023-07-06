package com.loki.britam.presentation.login

import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.google.firebase.FirebaseException
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.local.datastore.User
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepository
import com.loki.britam.presentation.VisionViewModel
import com.loki.britam.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val datastore: DataStoreStorage,
    private val firebase: FirebaseAccountRepository
): VisionViewModel(datastore) {

    val loginFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Email",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "Password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )


    fun login(email: String, password: String, openAndPopUp: (String, String) -> Unit) {

        launchCatching {

            try {
                isLoading.value = true
                firebase.authenticate(email, password)
                saveUser()
                isLoading.value = false
                openAndPopUp(Screens.HomeScreen.route, Screens.OnBoardingScreen.route)
            }
            catch (e: FirebaseException) {
                isLoading.value = false
                errorMessage.value = e.message ?: "something went wrong"
            }
        }
    }

    private fun saveUser() {

        viewModelScope.launch {
            firebase.currentUser.collect { user ->
                datastore.saveUser(
                    User(
                        name = user.username,
                        email = user.email,
                        isLoggedIn = firebase.hasUser
                    )
                )
            }
        }
    }
}