package com.loki.britam.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.presentation.common.Input
import com.loki.britam.presentation.login.LoginScreen
import com.loki.britam.presentation.login.LoginViewModel
import com.loki.britam.presentation.navigation.Screens
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    openScreen: (String) -> Unit
) {

    val formState = remember { viewModel.registerFormState }
    val names = formState.getState<TextFieldState>("Name")
    val email = formState.getState<TextFieldState>("Email")
    val password = formState.getState<TextFieldState>("Password")
    val conPassword = formState.getState<TextFieldState>("Confirm Password")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Register a new Account",
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(32.dp))

        Input(
            label = "Names",
            placeholder = "Names",
            value = names.value,
            onValueChange = { names.change(it) },
            errorMessage = names.errorMessage,
            isError = names.hasError,
            leadingIcon = Icons.Filled.AccountCircle
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        Input(
            label = "Email Address",
            placeholder = "Email Address",
            value = email.value,
            onValueChange = { email.change(it) },
            errorMessage = email.errorMessage,
            isError = email.hasError,
            leadingIcon = Icons.Filled.Email
        )

        Spacer(modifier = Modifier.height(8.dp))

        Input(
            label = "Password",
            placeholder = "Password",
            value = password.value,
            onValueChange = { password.change(it) },
            errorMessage = password.errorMessage,
            isError = password.hasError,
            leadingIcon = Icons.Filled.Lock,
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(8.dp))

        Input(
            label = "Confirm Password",
            placeholder = "Confirm Password",
            value = conPassword.value,
            onValueChange = { conPassword.change(it) },
            errorMessage = if(password.value != conPassword.value) "Password does not match"
                                else conPassword.errorMessage,
            isError = conPassword.hasError || password.value != conPassword.value,
            leadingIcon = Icons.Filled.Lock,
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Already have an account? Login",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clickable { openScreen(Screens.LoginScreen.route) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (formState.validate()) {
                    viewModel.signIn(
                        email.value,
                        password.value,
                        openScreen
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Register")
        }
    }
}