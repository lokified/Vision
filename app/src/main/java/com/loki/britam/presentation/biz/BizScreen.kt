package com.loki.britam.presentation.biz

import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.data.Company
import com.loki.britam.presentation.common.DropDownInput
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.theme.BritamTheme
import com.loki.britam.util.MonthUtils
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
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf

private val chartColors = listOf(Color.Blue, Color.Red)
@Composable
fun BizScreen(
    viewModel: BizViewModel
) {

    val companies = viewModel.otherCompanyList.map { it.name }
    var selectedBusiness by rememberSaveable { mutableStateOf("Other Business") }
    var selectedMonth by rememberSaveable { mutableStateOf("May") }

    var isExpanded by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TopSection(
            modifier = Modifier.padding(16.dp),
            visibility = viewModel.visibility.value,
            onSwitch = { viewModel.changeCompanyVisibility(it) }
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
                    otherLabel = selectedBusiness
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
                    companyList = companies
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

                                Text(text = selectedMonth, fontSize = 12.sp)

                                IconButton(onClick = { isExpanded = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.CalendarMonth,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary.copy(.8f)
                                    )
                                }
                            }
                        }

                        val months = MonthUtils.months

                        DropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false },
                            modifier = Modifier.height(300.dp),
                            scrollState = rememberScrollState(),
                            offset = DpOffset(-40.dp, -40.dp)
                        ) {
                            months.forEach {

                                DropdownMenuItem(
                                    onClick = {
                                        isExpanded = false
                                        selectedMonth = it
                                    },
                                    text = {
                                        Text(
                                            text = it,
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
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                MyCompanyExpenses(
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    visibility: Boolean,
    onSwitch: (Boolean) -> Unit
) {

    var isChecked by remember { mutableStateOf(visibility) }

    val state = if (isChecked) "Private" else "Public"

    Box(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Your Biz",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = state,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(2.dp))

            Switch(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onSwitch(it)
                }
            )
        }
    }
}

@Composable
fun GraphSection(
    modifier: Modifier = Modifier,
    otherLabel: String,
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
            legend = rememberLegend(otherLabel),
            modifier = Modifier.height(300.dp)
        )
    }
}

@Composable
fun rememberLegend(otherLabel: String) = verticalLegend(
    items = chartColors.mapIndexed { index, color ->
        verticalLegendItem(
            icon = shapeComponent(Shapes.pillShape, color),
            label = com.patrykandpatrick.vico.compose.component.textComponent(
                color = currentChartStyle.axis.axisLabelColor,
                textSize = 12.sp,
            ),
            labelText = if (index == 0) otherLabel else "My Business"
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
            placeholder = "Select Company",
            selectedValue = selected,
            onValueChange = selectedCompany,
            options = companyList
        )
    }
}

@Composable
fun MyCompanyExpenses(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(.05f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            RowSection(title = "Salaries", content = "ksh. 100,000")
            RowSection(title = "Rent and utilities", content = "ksh. 10,000")
            RowSection(title = "Marketing", content = "ksh. 2,000")
            RowSection(title = "Taxes", content = "ksh. 7,000")

            Divider(
                color = MaterialTheme.colorScheme.primary.copy(.1f)
            )
            Spacer(modifier = Modifier.height(16.dp))

            RowSection(title = "Total Profit", content = "ksh. 20,000")
        }
    }

}

@Composable
fun RowSection(
    modifier: Modifier = Modifier,
    title: String,
    content: String
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

        Text(text = content, fontWeight = FontWeight.Normal, fontSize = 16.sp)

    }
}

@Preview(
    showBackground = true
)
@Composable
fun BizPreview() {
    BritamTheme {
        BizScreen(viewModel = BizViewModel())
    }
}