package com.loki.britam.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.presentation.common.Input
import com.loki.britam.presentation.navigation.Screens
import com.loki.britam.presentation.theme.BritamTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    openScreen: (String) -> Unit,
    openAndPopScreen: (String, String) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.errorMessage.value) {

        if (viewModel.errorMessage.value.isNotBlank()) {
            Toast.makeText(context, viewModel.errorMessage.value, Toast.LENGTH_LONG).show()
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val formState = remember { viewModel.loginFormState }

    val email = formState.getState<TextFieldState>("Email")
    val password = formState.getState<TextFieldState>("Password")

    Box(modifier = Modifier.fillMaxSize()) {

        Column (
            modifier = Modifier.align(
                Alignment.TopCenter
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ){
            Image(
                imageVector = Icons.Filled.Grain,
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(top = 16.dp),
            )
            Text(text = "Vision")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Lets Log you in",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Input(
                placeholder = "Email Address",
                label = "Email",
                value = email.value,
                onValueChange = { email.change(it) },
                errorMessage = email.errorMessage,
                isError = email.hasError,
                leadingIcon = Icons.Filled.Email
            )

            Spacer(modifier = Modifier.height(8.dp))

            Input(
                placeholder = "Password",
                label = "Password",
                value = password.value,
                onValueChange = { password.change(it) },
                errorMessage = password.errorMessage,
                isError = password.hasError,
                leadingIcon = Icons.Filled.Lock,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Forgot Password?",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable { openScreen(Screens.ForgotPasswordScreen.route) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (formState.validate()) {
                        keyboardController?.hide()

                        viewModel.login(
                            email.value,
                            password.value,
                            openAndPopScreen
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "or", modifier = Modifier.align(CenterHorizontally))

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    openScreen(Screens.RegisterScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (viewModel.isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Center
                ) {

                    CircularProgressIndicator()
                }
            }
        }
    }
}