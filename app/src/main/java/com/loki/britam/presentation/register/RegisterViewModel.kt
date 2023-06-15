package com.loki.britam.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.britam.presentation.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

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

    fun signIn(email: String, password: String, openScreen: (String) -> Unit) {

        viewModelScope.launch {

            delay(3000L)
            openScreen(Screens.LoginScreen.route)
        }
    }
}