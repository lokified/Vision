package com.loki.britam.presentation.home

import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.data.Company
import com.loki.britam.data.companies
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.navigation.Screens
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    openScreen: (String) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            SearchSection(
                modifier = Modifier.padding(16.dp),
                onSearch = {  }
            )
        }

        item {
            HeaderSection(
                header = "My Company",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }


        item {
            NewCompanySection(
                companies = viewModel.companies,
                onAddCompanyClick = { openScreen(Screens.NewCompanyScreen.route) },
                onCompanyClick = { openScreen(Screens.CompanyScreen.navWithCompanyTitle(it)) }
            )
        }


        item {
            HeaderSection(
                header = "Insurance",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                trailingContent = {
                    TextButton(onClick = { openScreen(Screens.InsuranceScreen.route) }) {
                        Text(text = "See More")
                    }
                }
            )
        }


        item {
            ProductSection(
                products = listOf("Britam Biashara", "Theft Insurance",  "Fire Insurance"),
                onClick = { openScreen(Screens.ApplyScreen.navWithApplyTitle(it)) }
            )
        }

        item {
            HeaderSection(
                header = "Invest",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                trailingContent = {
                    TextButton(onClick = { openScreen(Screens.InvestScreen.route) }) {
                        Text(text = "See More")
                    }
                }
            )
        }

        item {
            ProductSection(
                products = listOf("Balanced Fund", "Equity Fund", "Britam Bond Plus Fund"),
                onClick = { openScreen(Screens.ApplyScreen.navWithApplyTitle(it)) }
            )
        }


        item {
            HeaderSection(
                header = "Other Companies",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }

        items(companies) { company ->
            CompanyCard(
                company = company,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {

    var value by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
    ) {

        TextField(
            value = value,
            onValueChange = { value = it },
            placeholder = {
                Text(text = "Search a company")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { onSearch(value) }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                }
            }
        )
    }
}

@Composable
fun NewCompanySection(
    modifier: Modifier = Modifier,
    companies: List<String>,
    onAddCompanyClick: () -> Unit,
    onCompanyClick: (String) -> Unit
) {

    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {

        item {
            Box(
                modifier = modifier
                    .size(70.dp)
                    .padding(horizontal = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { onAddCompanyClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        items(companies) { name ->
            BoxItem(name = name, onClick = onCompanyClick)
        }
    }
}

@Composable
fun ProductSection(
    modifier: Modifier = Modifier,
    products: List<String>,
    onClick: (String) -> Unit
) {

    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {

        items(products) { name ->
            BoxItem(name = name, onClick = onClick)
        }
    }
}

@Composable
fun BoxItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: (String) -> Unit
) {

    Box(
        modifier = modifier
            .size(height = 70.dp, width = 150.dp)
            .padding(horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = RoundedCornerShape(4.dp)
            ).clickable { onClick(name) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CompanyCard(
    modifier: Modifier = Modifier,
    company: Company
) {

    Box(modifier = modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface
            )
        ) {
            Text(
                text = company.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(12.dp)
            )

            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                
                Column(verticalArrangement = Arrangement.Center) {

                    CompanyItem(title = "Revenue", value = company.revenue)
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    CompanyItem(title = "Profits", value = company.profits)
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(verticalArrangement = Arrangement.Center) {

                    CompanyItem(title = "Customers", value = company.customers)

                    Spacer(modifier = Modifier.height(12.dp))

                    CompanyItem(title = "Expenditure", value = company.expenditure)
                }
            }
        }
    }
}

@Composable
fun CompanyItem(
    title: String,
    value: String
) {

    Column {
        Text(text = title, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun InsuranceScreenPreview() {
    BritamTheme {
        HomeScreen(viewModel = HomeViewModel(),openScreen = {})
    }
}