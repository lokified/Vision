package com.loki.britam.presentation.new_company

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.presentation.common.DefaultInput
import com.loki.britam.presentation.common.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCompanyScreen(
    viewModel: NewCompanyViewModel,
    popScreen: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit ) {
        if (viewModel.errorMessage.value.isNotBlank()) {
            Toast.makeText(
                context,
                viewModel.errorMessage.value,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val formState = remember { viewModel.newCompanyFormState }
    val name = formState.getState<TextFieldState>("BusinessName")
    val type = formState.getState<TextFieldState>("BusinessType")
    val location = formState.getState<TextFieldState>("BusinessLocation")
    val description = formState.getState<TextFieldState>("BusinessDescription")

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { popScreen() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "New OtherCompany")
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
                    value = description.value,
                    label = "Business Description",
                    onValueChange = { description.change(it) },
                    errorMessage = description.errorMessage,
                    isError = description.hasError,
                    modifier = Modifier.height(120.dp)
                )

                if (viewModel.isLoading.value) {
                    Loading()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        if (formState.validate()) {
                            viewModel.saveCompany(
                                Company(
                                    name = name.value,
                                    businessType = type.value,
                                    location = location.value,
                                    description = description.value
                                ),
                                popScreen
                            )
                        }
                    }
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}