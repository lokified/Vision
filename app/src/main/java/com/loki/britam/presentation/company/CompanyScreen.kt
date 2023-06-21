package com.loki.britam.presentation.company

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.presentation.common.EmptyBox
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.navigation.Screens
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun CompanyScreen(
    title: String,
    viewModel: CompanyViewModel,
    openScreen: (String) -> Unit
) {

    Scaffold () {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp)
                ) {

                    Text(
                        text = title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(
                            Alignment.BottomStart
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(
                            Alignment.BottomEnd
                        )
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
                            header = "Company Expenses",
                            modifier = Modifier.padding(vertical = 8.dp),
                            trailingContent = {
                                IconButton(
                                    onClick = { openScreen(Screens.NewExpenseScreen.route) }
                                ) {

                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = null
                                    )
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
                                modifier = Modifier.padding(vertical = 4.dp),
                                expense = expense
                            )
                        }
                    }
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

    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        shape = RoundedCornerShape(4.dp)
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
                text = companyInsurance.name,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            AssistChip(
                enabled = false,
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = if (companyInsurance.isActive) "Active" else "Inactive",
                        color = if (companyInsurance.isActive) Color.Green.copy(.5f) else Color.Red.copy(.5f),
                        fontSize = 12.sp
                    )
                },
            )
        }
    }

}

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    expense: Expense
) {

    val sign = if (expense.isProfit) "+" else "-"
    val color = if (expense.isProfit) Color.Green.copy(.7f) else Color.Red.copy(.7f)

    Card (
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        shape = RoundedCornerShape(4.dp)
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

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = null
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun CompanyScreenPreview() {
    BritamTheme {
        CompanyScreen(title = "Loki", viewModel = CompanyViewModel()) {

        }
    }
}