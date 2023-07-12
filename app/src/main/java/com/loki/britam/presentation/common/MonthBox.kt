package com.loki.britam.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.loki.britam.util.DateUtils
import java.util.Locale

@Composable
fun MonthDialog(
    onDismissClick: () -> Unit,
    onPositiveClick: (month: String, year: String) -> Unit
) {

    var selectedMonth by rememberSaveable { mutableStateOf(DateUtils.getCurrentMonth()) }
    var selectedYear by rememberSaveable { mutableIntStateOf(DateUtils.getCurrentYear()) }

    var monthYear by rememberSaveable { mutableStateOf(MonthYear.MONTH) }

    Dialog(onDismissRequest = onDismissClick) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "Select Month and Year", fontSize = 18.sp, color = Color.White)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {

                        Box(
                            modifier = Modifier.background(
                                color = if (monthYear == MonthYear.MONTH) Color.White else Color.Transparent,
                                shape = RoundedCornerShape(4.dp)
                            ).padding(4.dp)
                        ) {
                            Text(
                                text = selectedMonth,
                                fontSize = 18.sp,
                                color = if (monthYear == MonthYear.MONTH) MaterialTheme.colorScheme.primary else Color.White,
                                modifier = Modifier.clickable { monthYear = MonthYear.MONTH },
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier.background(
                                color = if (monthYear == MonthYear.YEAR) Color.White else Color.Transparent,
                                shape = RoundedCornerShape(4.dp)
                            ).padding(4.dp)
                        ) {
                            Text(
                                text = selectedYear.toString(),
                                fontSize = 18.sp,
                                color = if (monthYear == MonthYear.YEAR) MaterialTheme.colorScheme.primary else Color.White,
                                modifier = Modifier.clickable { monthYear = MonthYear.YEAR }
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {

                MonthBoxContent(
                    monthYear = monthYear,
                    onMonthSelected = { selectedMonth = it },
                    onYearSelected = { selectedYear = it.toInt() }
                )
            }


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    TextButton(onClick = onDismissClick) {
                        Text(text = "CANCEL")
                    }

                    TextButton(
                        onClick = {
                            onPositiveClick(selectedMonth, selectedYear.toString())
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }
}

@Composable
fun MonthBoxContent(
    monthYear: MonthYear,
    onYearSelected: (String) -> Unit = {},
    onMonthSelected: (String) -> Unit = {},
    selectedColor: Color = MaterialTheme.colorScheme.primary
) {

    val yearList = 1970..DateUtils.getCurrentYear()
    var selectedMonth by rememberSaveable { mutableStateOf("") }
    var selectedYear by rememberSaveable { mutableStateOf("") }

    LazyColumn(contentPadding = PaddingValues(8.dp)) {

        when (monthYear) {
            MonthYear.MONTH -> {

                items(DateUtils.months) {
                    TextButton(
                        onClick = {
                            onMonthSelected(it)
                            selectedMonth = it
                        }
                    ) {
                        Text(
                            text = it,
                            color = if (selectedMonth == it) selectedColor else MaterialTheme.colorScheme.onBackground,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            MonthYear.YEAR -> {

                items(yearList.toList().reversed()) {

                    TextButton(
                        onClick = {
                            onYearSelected(it.toString())
                            selectedYear = it.toString()
                        }
                    ) {
                        Text(
                            text = it.toString(),
                            color = if (selectedYear == it.toString()) selectedColor else MaterialTheme.colorScheme.onBackground,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

enum class MonthYear {
    MONTH,
    YEAR
}