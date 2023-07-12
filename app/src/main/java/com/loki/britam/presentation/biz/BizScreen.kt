package com.loki.britam.presentation.biz

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loki.britam.data.remote.firebase.models.Company
import com.loki.britam.data.remote.firebase.models.Expense
import com.loki.britam.presentation.common.DropDownInput
import com.loki.britam.presentation.common.EmptyBox
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.common.MonthDialog
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf

private val chartColors = listOf(Color.Blue, Color.Red)
@Composable
fun BizScreen(
    viewModel: BizViewModel
) {

    val myCompanies by viewModel.companies.collectAsStateWithLifecycle(initialValue = emptyList())
    val otherCompanies = viewModel.otherCompanyList.map { it.name }
    var selectedBusiness by rememberSaveable { mutableStateOf("Other Business") }
    var isMonthDropDownExpanded by remember { mutableStateOf(false) }
    var isActiveCompanyDropDownExpanded by remember { mutableStateOf(false) }

    val expenseData by viewModel.expenseData


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopSection(
            modifier = Modifier.padding(16.dp),
            activeCompany = viewModel.activeCompany.value ?: Company(name = "Your Biz"),
            companies = myCompanies,
            onCompanyChange = {
                viewModel.updateActiveCompany(it)
                isActiveCompanyDropDownExpanded = false
                              },
            isDropDownExpanded = isActiveCompanyDropDownExpanded,
            onClickDropDown = { isActiveCompanyDropDownExpanded = true },
            onDismissDropDown = { isActiveCompanyDropDownExpanded = false },
            visibility = viewModel.visibility.value,
            onVisibilityChange = viewModel::changeCompanyVisibility
        )

        LazyColumn (
            contentPadding = PaddingValues(16.dp)
        ) {

            item {
                GraphSection(
                    model = entryModelOf(
                        viewModel.comparableModel.value,
                        viewModel.myCompany.data[0].model
                    ),
                    otherLabel = selectedBusiness,
                    myBusinessLabel = viewModel.activeCompany.value!!.name
                )
            }

            item {
                CompanySelectionSection(
                    modifier = Modifier.padding(vertical = 12.dp),
                    selected = selectedBusiness,
                    selectedCompany = {
                        selectedBusiness = it
                        viewModel.setSelectedModel(it)
                    },
                    companyList = otherCompanies
                )
            }

            item {
                HeaderSection(
                    header = "My expenses",
                    trailingContent = {

                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary.copy(.1f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 4.dp)
                        ) {
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = "${viewModel.monthSelected.value} - ${viewModel.yearSelected.value}",
                                    fontSize = 12.sp
                                )

                                IconButton(onClick = { isMonthDropDownExpanded = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.CalendarMonth,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary.copy(.8f)
                                    )
                                }
                            }
                        }

                        if (isMonthDropDownExpanded) {
                            MonthDialog(
                                onDismissClick = { isMonthDropDownExpanded = false },
                                onPositiveClick = { month, year ->
                                    isMonthDropDownExpanded = false
                                    viewModel.onMonthSelectedChange(month)
                                    viewModel.onYearSelectedChange(year)
                                    viewModel.setExpenseDataDisplay()
                                }
                            )
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MyCompanyExpenses(
                    modifier = Modifier.padding(vertical = 12.dp),
                    expense = expenseData
                )
            }
        }
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    activeCompany: Company,
    companies: List<Company>,
    onCompanyChange: (Company) -> Unit,
    isDropDownExpanded: Boolean,
    onClickDropDown: () -> Unit,
    onDismissDropDown: () -> Unit,
    visibility: Boolean,
    onVisibilityChange: (Boolean) -> Unit
) {

    val state = if (visibility) "Private" else "Public"

    Box(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Column {
                Text(
                    text = activeCompany.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )

                DropdownMenu(
                    expanded = isDropDownExpanded,
                    onDismissRequest = onDismissDropDown,
                    modifier = Modifier,
                    scrollState = rememberScrollState(),
                    offset = DpOffset(0.dp, 0.dp)
                ) {
                    companies.forEach {

                        DropdownMenuItem(
                            onClick = {
                                onCompanyChange(it)
                            },
                            text = {
                                Text(
                                    text = it.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = 16.dp,
                                            vertical = 12.dp
                                        ),
                                )
                            }
                        )
                    }
                }
            }

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onClickDropDown() }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = state,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(2.dp))

            Switch(
                checked = visibility,
                onCheckedChange = {
                    onVisibilityChange(it)
                }
            )
        }
    }
}

@Composable
fun GraphSection(
    modifier: Modifier = Modifier,
    otherLabel: String,
    myBusinessLabel: String,
    model: ChartEntryModel
) {

    Box(modifier = modifier.fillMaxWidth()) {
        
        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = chartColors[0]
                    ),
                    lineSpec(
                        lineColor = chartColors[1]
                    )
                )
            ),
            model = model,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(),
            legend = rememberLegend(otherLabel, myBusinessLabel),
            modifier = Modifier.height(300.dp)
        )
    }
}

@Composable
fun rememberLegend(otherLabel: String, myBusinessLabel: String) = verticalLegend(
    items = chartColors.mapIndexed { index, color ->
        verticalLegendItem(
            icon = shapeComponent(Shapes.pillShape, color),
            label = com.patrykandpatrick.vico.compose.component.textComponent(
                color = currentChartStyle.axis.axisLabelColor,
                textSize = 12.sp,
            ),
            labelText = if (index == 0) otherLabel else myBusinessLabel
        )
    },
    iconSize = 8.dp,
    iconPadding = 8.dp,
    spacing = 4.dp,
    padding = dimensionsOf(top = 4.dp)
)

@Composable
fun CompanySelectionSection(
    modifier: Modifier = Modifier,
    selected: String,
    selectedCompany: (String) -> Unit,
    companyList: List<String>
) {

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Select Companies to Compare", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        DropDownInput(
            placeholder = "Select OtherCompany",
            selectedValue = selected,
            onValueChange = selectedCompany,
            options = companyList
        )
    }
}

@Composable
fun MyCompanyExpenses(
    modifier: Modifier = Modifier,
    expense: Expense
) {

    val text = if (!expense.loss) "Total Profit" else "Total Loss"
    val sign = if (!expense.loss) "+ Ksh. ${expense.profitLoss}" else "- Ksh. ${expense.profitLoss}"
    val color = if (!expense.loss) Color.Green.copy(.7f) else Color.Red.copy(.7f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(.05f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        if (expense.profitLoss.isBlank()) {
            EmptyBox(content = "No records")
        }
        else {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                RowSection(title = "Salaries", content = "ksh. ${expense.salary}")
                RowSection(
                    title = "Rent and utilities",
                    content = "ksh. ${expense.rentAndUtilities}"
                )
                RowSection(title = "Marketing", content = "ksh. ${expense.marketing}")
                RowSection(title = "Taxes", content = "ksh. ${expense.taxes}")

                Divider(
                    color = MaterialTheme.colorScheme.primary.copy(.1f)
                )
                Spacer(modifier = Modifier.height(16.dp))

                RowSection(title = text, content = sign, contentColor = color)
            }
        }
    }

}

@Composable
fun RowSection(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    contentColor: Color = MaterialTheme.colorScheme.onBackground
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(text = title)

        Spacer(modifier = Modifier.weight(1f))

        Text(text = content, fontWeight = FontWeight.Normal, fontSize = 16.sp, color = contentColor)

    }
}