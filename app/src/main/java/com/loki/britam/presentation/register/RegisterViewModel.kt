package com.loki.britam.presentation.register

import androidx.compose.runtime.mutableStateOf
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.google.firebase.FirebaseException
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.remote.firebase.auth.FirebaseAccountRepository
import com.loki.britam.presentation.VisionViewModel
import com.loki.britam.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val datastore: DataStoreStorage,
    private val firebase: FirebaseAccountRepository
    ): VisionViewModel(datastore) {

    val registerFormState = FormState(
        fields = listOf(
            TextFieldState(
                name = "Name",
                validators = listOf(
                    Validators.Required()
                )
            ),
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
            ),
            TextFieldState(
                name = "Confirm Password",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    fun register(name: String, email: String, password: String, openScreen: (String) -> Unit) {

        launchCatching {

            try {

                isLoading.value = true
                firebase.createAccount(name, email, password)
                isLoading.value = false
                openScreen(Screens.LoginScreen.route)
            } catch (e: FirebaseException) {
                isLoading.value = false
                errorMessage.value = e.message ?: "Something went wrong"
            }
        }
    }
}