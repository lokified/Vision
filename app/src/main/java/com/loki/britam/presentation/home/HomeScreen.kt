package com.loki.britam.presentation.home

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.data.Company
import com.loki.britam.data.companies
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
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
                onAddCompanyClick = {}
            )
        }


        item {
            HeaderSection(
                header = "My Insurance",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                trailingText = "Explore",
                onClickTrailingText = {  }
            )
        }


        item {
            InsuranceSection(insurances = viewModel.insurances)
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
fun HeaderSection(
    modifier: Modifier = Modifier,
    header: String,
    trailingText: String? = null,
    onClickTrailingText: () -> Unit = {}
) {

    Box(modifier = modifier.fillMaxWidth()) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = header,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            if (trailingText != null) {
                TextButton(onClick = { onClickTrailingText() }) {
                    Text(text = trailingText)
                }
            }
        }

    }
}

@Composable
fun NewCompanySection(
    modifier: Modifier = Modifier,
    companies: List<String>,
    onAddCompanyClick: () -> Unit
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
                        color = MaterialTheme.colorScheme.surfaceVariant,
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
            BoxItem(name = name)
        }
    }
}

@Composable
fun InsuranceSection(
    modifier: Modifier = Modifier,
    insurances: List<String>
) {

    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {

        items(insurances) { name ->
            BoxItem(name = name)
        }
    }
}

@Composable
fun BoxItem(
    modifier: Modifier = Modifier,
    name: String
) {

    Box(
        modifier = modifier
            .size(height = 70.dp, width = 150.dp)
            .padding(horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
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
            shape = RoundedCornerShape(4.dp)
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
    showBackground = true
)
@Composable
fun HomePreview() {
    BritamTheme {
        HomeScreen(viewModel = HomeViewModel())
    }
}