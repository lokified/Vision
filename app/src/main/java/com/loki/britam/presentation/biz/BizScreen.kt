package com.loki.britam.presentation.biz

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.presentation.common.DropDownInput
import com.loki.britam.presentation.common.HeaderSection
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
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf

private val model = entryModelOf(entriesOf(0, 2, 2, 3, 1), entriesOf(0, 3, 1, 2, 3))
private val chartColors = listOf(Color.Blue, Color.Red)
@Composable
fun BizScreen(
    viewModel: BizViewModel
) {

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
                GraphSection()
            }

            item {
                CompanySelectionSection(
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }

            item {
                HeaderSection(
                    header = "My expenses",
                    trailingContent = {
                        Text(text = "Change Month", fontSize = 12.sp)

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.CalendarMonth,
                                contentDescription = null
                            )
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
    modifier: Modifier = Modifier
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
            legend = rememberLegend()
        )
    }
}

@Composable
fun rememberLegend() = verticalLegend(
    items = chartColors.mapIndexed { index, color ->
        verticalLegendItem(
            icon = shapeComponent(Shapes.pillShape, color),
            label = com.patrykandpatrick.vico.compose.component.textComponent(
                color = currentChartStyle.axis.axisLabelColor,
                textSize = 12.sp,
            ),
            labelText = if (index == 0) "Other Company" else "My Company"
        )
    },
    iconSize = 8.dp,
    iconPadding = 8.dp,
    spacing = 4.dp,
    padding = dimensionsOf(top = 4.dp)
)

@Composable
fun CompanySelectionSection(
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxWidth()) {

        Text(text = "Select Companies to Compare", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        DropDownInput(
            placeholder = "Select Company",
            onValueChange = { },
            options = listOf("Safaricom", "Airtel", "pics")
        )
    }
}

@Composable
fun MyCompanyExpenses(
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxWidth()) {

        RowSection(title = "Salaries", content = "ksh. 100,000")
        RowSection(title = "Rent and utilities", content = "ksh. 10,000")
        RowSection(title = "Marketing", content = "ksh. 2,000")
        RowSection(title = "Taxes", content = "ksh. 7,000")

        Spacer(modifier = Modifier.height(16.dp))

        RowSection(title = "Total Profit", content = "ksh. 20,000")
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

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = content, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}
