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
import androidx.compose.material3.rememberDateRangePickerState
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
import com.loki.britam.presentation.common.DatePicker
import com.loki.britam.presentation.common.Loading
import com.loki.britam.presentation.common.SingleBorderInput
import com.loki.britam.util.DateUtils.formatDate
import com.loki.britam.util.DateUtils.toMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditExpenseScreen(
    viewModel: CompanyViewModel,
    popScreen: () -> Unit
) {

    val expense by viewModel.expense
    var selectedStartMonth by remember { mutableStateOf("") }
    var selectedEndMonth by remember { mutableStateOf("") }

    val pickerState = rememberDateRangePickerState()
    var isPickerVisible by remember { mutableStateOf(false) }

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
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = "Select Month", fontSize = 12.sp)
                        IconButton(onClick = { isPickerVisible = true }) {
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary.copy(.8f)
                            )
                        }
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

            Text(
                text = if (selectedEndMonth.isNotBlank()) getMonth(selectedStartMonth, selectedEndMonth)
                    else if (expense.month.isNotBlank()) expense.month
                    else "",
                fontSize = 15.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.primary.copy(.8f)
            )

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(18.dp))


                Spacer(modifier = Modifier.height(12.dp))

                SingleBorderInput(
                    value = expense.salary,
                    label = "Amount used for Salaries",
                    onValueChange = viewModel::onSalaryFieldChange,
                    errorMessage = "",
                    isError = false,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                SingleBorderInput(
                    value = expense.taxes,
                    label = "Taxes Paid",
                    onValueChange = viewModel::onTaxFieldChange,
                    errorMessage = "",
                    isError = false,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                SingleBorderInput(
                    value = expense.marketing,
                    label = "Amount used for Marketing",
                    onValueChange = viewModel::onMarketingFieldChange,
                    errorMessage = "",
                    isError = false,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                SingleBorderInput(
                    value = expense.rentAndUtilities,
                    label = "Rent And Utilities",
                    onValueChange = viewModel::onUtilitiesFieldChange,
                    errorMessage = "",
                    isError = false,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SingleBorderInput(
                        value = expense.profitLoss,
                        label = "Enter Profit/Loss",
                        onValueChange = viewModel::onProfitLossFieldChange,
                        errorMessage = "",
                        isError = false,
                        keyboardType = KeyboardType.Number,
                        isProfit = !expense.loss,
                        isProfitField = true,
                        modifier = Modifier.fillMaxWidth(.8f)
                    )

                    Checkbox(
                        checked = expense.loss,
                        onCheckedChange = viewModel::onIsProfitLossChange
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
                        viewModel.addCompanyExpense(
                            popScreen
                        )
                    }
                ) {
                    Text(text = if (viewModel.isEditScreen.value) "Edit Expense" else "Add Expense")
                }
            }
        }
    }

    if (isPickerVisible) {

        DatePicker(
            pickerState = pickerState,
            onSave = { start, end ->
                isPickerVisible = false
                selectedStartMonth = start!!.formatDate()
                selectedEndMonth = end!!.formatDate()
                viewModel.onStartDateChange(start)
                viewModel.onEndDateChange(end)
                viewModel.onMonthChange(getMonth(start.toMonth(), end.toMonth()))
            },
            onDismiss = {
                isPickerVisible = false
            }
        )

        if (expense.endDate != null) {
            pickerState.setSelection(
                startDateMillis = expense.startDate,
                endDateMillis = expense.endDate
            )
        }
    }
}

fun getMonth(selectedStartMonth: String, selectedEndMonth: String) = if (selectedStartMonth == selectedEndMonth)
    selectedEndMonth
else
    "$selectedStartMonth - $selectedEndMonth"