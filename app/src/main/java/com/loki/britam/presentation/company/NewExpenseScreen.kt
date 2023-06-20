package com.loki.britam.presentation.company

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.presentation.common.DefaultInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewExpenseScreen(
    viewModel: CompanyViewModel,
    popScreen: () -> Unit
) {

    val formState = remember { viewModel.newExpenseFormState }
    val salary = formState.getState<TextFieldState>("Salary")
    val taxes = formState.getState<TextFieldState>("Taxes")
    val marketing = formState.getState<TextFieldState>("Marketing")
    val profit = formState.getState<TextFieldState>("Profit")

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { popScreen() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "New Expense")
                },
                actions = {
                    Text(text = "Month", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = null)
                    }
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


                Spacer(modifier = Modifier.height(12.dp))

                DefaultInput(
                    value = salary.value,
                    label = "Amount used for Salaries",
                    onValueChange = { salary.change(it) },
                    errorMessage = salary.errorMessage,
                    isError = salary.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = taxes.value,
                    label = "Taxes Paid",
                    onValueChange = { taxes.change(it) },
                    errorMessage = taxes.errorMessage,
                    isError = taxes.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = marketing.value,
                    label = "Amount used for Marketing",
                    onValueChange = { marketing.change(it) },
                    errorMessage = marketing.errorMessage,
                    isError = marketing.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultInput(
                    value = profit.value,
                    label = "Enter Profit/Loss",
                    onValueChange = { profit.change(it) },
                    errorMessage = profit.errorMessage,
                    isError = profit.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(55.dp))
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
                            popScreen()
                        }
                    }
                ) {
                    Text(text = "Add Expense")
                }
            }
        }
    }
}