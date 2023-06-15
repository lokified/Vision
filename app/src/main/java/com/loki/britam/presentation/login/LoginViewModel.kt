package com.loki.britam.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.britam.presentation.navigation.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

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

        openAndPopUp(Screens.HomeScreen.route, Screens.LoginScreen.route)
    }
}