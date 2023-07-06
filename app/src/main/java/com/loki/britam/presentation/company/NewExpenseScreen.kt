package com.loki.britam.presentation.company

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsc.form_builder.TextFieldState
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.presentation.common.DefaultInput
import com.loki.britam.presentation.common.Loading
import com.loki.britam.presentation.common.MonthBox
import com.loki.britam.presentation.common.SingleBorderInput
import com.loki.britam.util.MonthUtils

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

    val currentMonth = MonthUtils.getCurrentMonth()
    var selectedMonth by remember { mutableStateOf(currentMonth) }
    var isExpanded by remember { mutableStateOf(false) }

    var isLossChecked by remember { mutableStateOf(false) }

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
                    Text(text = selectedMonth, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))

                    IconButton(onClick = { isExpanded = true }) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary.copy(.8f)
                        )
                    }

                    MonthBox(
                        isExpanded = isExpanded,
                        onClick = {
                            selectedMonth = it
                            isExpanded = false
                        },
                        onDismiss = { isExpanded = false }
                    )
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

                SingleBorderInput(
                    value = salary.value,
                    label = "Amount used for Salaries",
                    onValueChange = { salary.change(it) },
                    errorMessage = salary.errorMessage,
                    isError = salary.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                SingleBorderInput(
                    value = taxes.value,
                    label = "Taxes Paid",
                    onValueChange = { taxes.change(it) },
                    errorMessage = taxes.errorMessage,
                    isError = taxes.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                SingleBorderInput(
                    value = marketing.value,
                    label = "Amount used for Marketing",
                    onValueChange = { marketing.change(it) },
                    errorMessage = marketing.errorMessage,
                    isError = marketing.hasError,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SingleBorderInput(
                        value = profit.value,
                        label = "Enter Profit/Loss",
                        onValueChange = { profit.change(it) },
                        errorMessage = profit.errorMessage,
                        isError = profit.hasError,
                        keyboardType = KeyboardType.Number,
                        isProfit = !isLossChecked,
                        isProfitField = true,
                        modifier = Modifier.fillMaxWidth(.8f)
                    )

                    Checkbox(
                        checked = isLossChecked,
                        onCheckedChange = { isLossChecked = it}
                    )

                    Text(text = "Loss")
                }


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
                            viewModel.addCompanyExpense(
                                Expense(
                                    month = selectedMonth,
                                    salary = salary.value,
                                    marketing = marketing.value,
                                    profitLoss = profit.value,
                                    taxes = taxes.value,
                                    isProfit = isLossChecked
                                ),
                                popScreen
                            )
                        }
                    }
                ) {
                    Text(text = "Add Expense")
                }
            }
        }
    }
}