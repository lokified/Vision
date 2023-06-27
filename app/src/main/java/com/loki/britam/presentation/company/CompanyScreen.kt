package com.loki.britam.presentation.company

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.presentation.common.BackgroundCurves
import com.loki.britam.presentation.common.EmptyBox
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.common.IconBackgroundCurves
import com.loki.britam.presentation.navigation.Screens
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun CompanyScreen(
    title: String,
    viewModel: CompanyViewModel,
    openScreen: (String) -> Unit
) {


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

            Text(
                text = title,
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
                Text(text = "Location", fontSize = 12.sp)
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

            if (viewModel.insurance.isEmpty()) {
                item {
                    EmptyBox(content = "No active Insurance")
                }
            }
            else {
                items(viewModel.insurance) { insurance ->

                    ActiveInsuranceCard(
                        modifier = Modifier.padding(vertical = 4.dp),
                        companyInsurance = insurance
                    )
                }
            }

            item {
                HeaderSection(
                    header = "My Investments",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (viewModel.investments.isEmpty()) {
                item {
                    EmptyBox(content = "No active Investments")
                }
            }
            else {
                items(viewModel.investments) { investment ->


                }
            }

            item {
                HeaderSection(
                    header = "My Loans",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (viewModel.loans.isEmpty()) {
                item {
                    EmptyBox(content = "No active Loans")
                }
            }
            else {
                items(viewModel.loans) { loan ->

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

            if (viewModel.expenses.isEmpty()) {
                item {
                    EmptyBox(content = "No expenses recorded")
                }
            }
            else {
                items(viewModel.expenses) { expense ->

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
    companyInsurance: CompanyInsurance
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
                text = companyInsurance.name,
                fontSize = 18.sp,
                color = Color.White.copy(.8f)
            )

        }

        Text(
            text = if (companyInsurance.isActive) "Active" else "Inactive",
            color = if (companyInsurance.isActive) Color.Green.copy(.8f) else Color.Red.copy(.8f),
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

    val sign = if (expense.isProfit) "+" else "-"
    val color = if (expense.isProfit) Color.Green.copy(.7f) else Color.Red.copy(.7f)

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
                text = "$sign${expense.amount}",
                fontSize = 18.sp,
                color = color
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
    heightDp = 2000
)
@Composable
fun CompanyScreenPreview() {
    BritamTheme {
        CompanyScreen(title = "Loki", viewModel = CompanyViewModel()) {

        }
    }
}