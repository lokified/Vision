package com.loki.britam.presentation.home

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.britam.data.otherCompanies
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.permissions.PermissionAction
import com.loki.britam.permissions.PermissionDialog
import com.loki.britam.presentation.common.BoxItem
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.common.IconBoxItem
import com.loki.britam.presentation.navigation.Screens
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    openScreen: (String) -> Unit
) {

    val companyList by viewModel.companies.collectAsStateWithLifecycle(initialValue = emptyList())
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    PermissionDialog(
        context = context,
        permission = Manifest.permission.POST_NOTIFICATIONS,
        permissionRationale = Manifest.permission.POST_NOTIFICATIONS,
        snackbarHostState = snackbarHostState,
        permissionAction =  { action ->
            when(action) {
                PermissionAction.PermissionGranted -> {
                    scope.launch {
                        snackbarHostState.showSnackbar("Notifications permission granted")
                    }
                }
                PermissionAction.PermissionDenied -> {

                }
            }
        }
    )

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
                header = "My OtherCompany",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }


        item {
            NewCompanySection(
                companies = companyList,
                onAddCompanyClick = { openScreen(Screens.NewCompanyScreen.route) },
                onCompanyClick = { openScreen(Screens.CompanyScreen.navWithCompanyId(it)) }
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
                header = "Do you need a loan for your business ?",
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
            )
        }

        item {
            LoanSection(
                products = listOf("Anzisha Loan", "Britam Loan"),
                onClick = { openScreen(Screens.ApplyScreen.navWithApplyTitle(it)) }
            )
        }


        item {
            HeaderSection(
                header = "Other Companies",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }

        items(otherCompanies) { company ->
            CompanyCard(
                otherCompany = company,
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
    companies: List<Company>,
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
                        color = MaterialTheme.colorScheme.primary.copy(.1f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { onAddCompanyClick() },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Create", color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp
                    )
                }
            }
        }

        items(companies) { company ->
            IconBoxItem(
                name = company.name,
                icon = Icons.Filled.Business,
                onClick = { onCompanyClick(company.id) }
            )
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
fun LoanSection(
    modifier: Modifier = Modifier,
    products: List<String>,
    onClick: (String) -> Unit
) {

    LazyRow(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {

        items(products) { name ->
            IconBoxItem(name = name, icon = Icons.Filled.Money ,onClick = { onClick(name) })
        }
    }
}

@Composable
fun CompanyCard(
    modifier: Modifier = Modifier,
    otherCompany: com.loki.britam.data.OtherCompany
) {

    val data = otherCompany.data[0]

    Box(modifier = modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(.05f)
            )
        ) {
            Text(
                text = otherCompany.name,
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

                    CompanyItem(title = "Revenue", value = data.revenue)
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    CompanyItem(title = "Profits", value = data.profits)
                }

                Spacer(modifier = Modifier.weight(1f))

                Column(verticalArrangement = Arrangement.Center) {

                    CompanyItem(title = "Customers", value = data.customers)

                    Spacer(modifier = Modifier.height(12.dp))

                    CompanyItem(title = "Expenditure", value = data.expenditure)
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