package com.loki.britam.presentation.new_company

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.presentation.common.DefaultInput
import com.loki.britam.presentation.theme.BritamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCompanyScreen(
    viewModel: NewCompanyViewModel,
    popScreen: () -> Unit
) {

    val formState = remember { viewModel.newCompanyFormState }
    val name = formState.getState<TextFieldState>("BusinessName")
    val type = formState.getState<TextFieldState>("BusinessType")
    val location = formState.getState<TextFieldState>("BusinessLocation")
    val description = formState.getState<TextFieldState>("BusinessDescription")
    val estimate = formState.getState<TextFieldState>("Estimate")

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { popScreen() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "New Company")
                }
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Create a new company with us",
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                DefaultInput(
                    value = name.value,
                    label = "Business Name",
                    onValueChange = { name.change(it) },
                    errorMessage = name.errorMessage,
                    isError = name.hasError
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = type.value,
                    label = "Business Type",
                    onValueChange = { type.change(it) },
                    errorMessage = type.errorMessage,
                    isError = type.hasError
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = location.value,
                    label = "Business Location",
                    onValueChange = { location.change(it) },
                    errorMessage = location.errorMessage,
                    isError = location.hasError
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = estimate.value,
                    label = "Enter Estimate Amount",
                    onValueChange = { estimate.change(it) },
                    errorMessage = estimate.errorMessage,
                    isError = estimate.hasError,
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = description.value,
                    label = "Business Description",
                    onValueChange = { description.change(it) },
                    errorMessage = description.errorMessage,
                    isError = description.hasError,
                    modifier = Modifier.height(120.dp)
                )

                Spacer(modifier = Modifier.height(55.dp))
            }

            Box(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        if (formState.validate()) {
                            popScreen()
                        }
                    }
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun NewCompanyScreenPreview() {

    BritamTheme {
        NewCompanyScreen(popScreen = {}, viewModel = NewCompanyViewModel())
    }
}