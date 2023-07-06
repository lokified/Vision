package com.loki.britam.presentation.company

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.data.remote.firebase.models.Insurance
import com.loki.britam.data.remote.firebase.models.Loan
import com.loki.britam.presentation.common.BackgroundCurves
import com.loki.britam.presentation.common.EmptyBox
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.common.IconBackgroundCurves
import com.loki.britam.presentation.navigation.Screens

@Composable
fun CompanyScreen(
    viewModel: CompanyViewModel,
    openScreen: (String) -> Unit
) {

    val company = viewModel.company.value
    val data by viewModel.data.collectAsStateWithLifecycle()
    val companyData = data.companyData

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(.8f),
                            MaterialTheme.colorScheme.primary.copy(.5f),
                            MaterialTheme.colorScheme.primary.copy(.2f),
                            Color.Transparent
                        )
                    )
                )
        ) {

            company?.let { company->

                Text(
                    text = company.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(
                            Alignment.BottomStart
                        )
                        .padding(start = 16.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(
                            Alignment.BottomEnd
                        )
                        .padding(end = 16.dp)
                ) {
                    Icon(imageVector = Icons.Filled.LocationCity, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = company.location, fontSize = 12.sp)
                }
            }
        }

        LazyColumn (
            contentPadding = PaddingValues(16.dp)
        ) {

            item {
                HeaderSection(
                    header = "My Insurance",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            if (companyData.insurances!!.isEmpty()) {
                item {
                    EmptyBox(content = "No active Insurance")
                }
            } else {
                items(companyData.insurances) { insurance ->

                    ActiveInsuranceCard(
                        modifier = Modifier.padding(vertical = 4.dp),
                        insurance = insurance
                    )
                }
            }

            item {
                HeaderSection(
                    header = "My Investments",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (companyData.investment!!.isEmpty()) {
                item {
                    EmptyBox(content = "No active Investments")
                }
            } else {
                items(companyData.investment) { investment ->


                }
            }

            item {
                HeaderSection(
                    header = "My Loans",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (companyData.loans!!.isEmpty()) {
                item {
                    EmptyBox(content = "No active Loans")
                }
            } else {
                items(companyData.loans) { loan ->

                    ActiveLoanCard(
                        modifier = Modifier.padding(vertical = 4.dp),
                        loan = loan
                    )
                }
            }

            item {

                HeaderSection(
                    header = "Company Expenses",
                    modifier = Modifier.padding(vertical = 8.dp),
                    trailingContent = {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(.1f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { openScreen(Screens.NewExpenseScreen.route) },
                        ) {

                            Row(
                                modifier = Modifier
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )

                                Text(text = "Add", color = MaterialTheme.colorScheme.primary)
                            }

                        }
                    }
                )
            }

            if (companyData.expense!!.isEmpty()) {
                item {
                    EmptyBox(content = "No expenses recorded")
                }
            } else {
                items(companyData.expense) { expense ->

                    ExpenseCard(
                        expense = expense
                    )
                }
            }
        }
    }
}

@Composable
fun ActiveInsuranceCard(
    modifier: Modifier = Modifier,
    insurance: Insurance
) {

    Box (
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .height(100.dp),
    ) {

        BackgroundCurves()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = insurance.name,
                fontSize = 18.sp,
                color = Color.White.copy(.8f)
            )

        }

        Text(
            text = if (insurance.isActive) "Active" else "Inactive",
            color = if (insurance.isActive) Color.Green.copy(.8f) else Color.Red.copy(.8f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}

@Composable
fun ActiveLoanCard(
    modifier: Modifier = Modifier,
    loan: Loan
) {

    Box (
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .height(100.dp),
    ) {

        IconBackgroundCurves()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = loan.name,
                fontSize = 18.sp,
                color = Color.White.copy(.8f)
            )

        }

        Icon(
            imageVector = Icons.Filled.Money,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
                .align(Alignment.TopStart),
            tint = Color.White.copy(.8f)
        )

        Text(
            text = "Due Date - ${loan.dueDate}",
            fontSize = 12.sp,
            color = Color.White.copy(.8f),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        )

        Text(
            text = if (loan.isActive) "Active" else "Inactive",
            color = if (loan.isActive) Color.Green.copy(.8f) else Color.Red.copy(.8f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
    }
}

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    expense: Expense
) {

//    val sign = if (expense.isProfit) "+${expense.profitLoss}" else "-${expense.profitLoss}"
//    val color = if (expense.isProfit) Color.Green.copy(.7f) else Color.Red.copy(.7f)

    Box (
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary.copy(.025f)
            ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = expense.month,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = expense.profitLoss,
                fontSize = 18.sp,
            )
        }
    }
}