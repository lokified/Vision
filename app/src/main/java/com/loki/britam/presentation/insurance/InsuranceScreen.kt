package com.loki.britam.presentation.insurance

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.presentation.theme.BritamTheme


enum class TabPage(val title: String) {
    BUSINESS("Business"),
    PERSONAL("Personal")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsuranceScreen(
    openScreen: (String) -> Unit,
    popScreen: () -> Unit
) {

    val bInsurances = listOf( "Britam Biashara", "Theft Insurance",  "Fire Insurance", "Cyber Insurance", "Liability Insurance", "Political Violence and Terrorism Insurance")
    val pInsurances = listOf( "Health Insurance", "Britam Motor Insurance", "Accident Insurance", "Home Insurance", "Family Protection Plans", "Funeral Covers", "Fire and Burglary")

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { popScreen() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                },
                title = {
                    Text(text = "Insurances")
                }
            )
        }
    ) {
       
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column {
                var currentTab by rememberSaveable { mutableStateOf(TabPage.BUSINESS) }

                InsuranceTabs(
                    onSelected = { selected -> currentTab = selected }
                )

                Crossfade(
                    targetState = currentTab,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                ) { tab ->
                    when(tab) {
                        TabPage.BUSINESS -> Tab(insurances = bInsurances, openScreen = openScreen)
                        TabPage.PERSONAL -> Tab(insurances = pInsurances, openScreen = openScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun Tab(
    insurances: List<String>,
    openScreen: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {


        items(insurances) { insurance ->

            InsuranceCard(
                title = insurance,
                modifier = Modifier.padding(8.dp),
                onClick = openScreen
            )
        }
    }
}

@Composable
fun InsuranceCard(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: (String) -> Unit
) {

    val width = LocalView.current.width.dp

    Box(
        modifier = modifier
            .background(
                color = color.copy(0.25f),
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = .5.dp,
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
            .size(
                height = 150.dp,
                width = width / 2
            )
            .padding(16.dp)
            .clickable { onClick(title) },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InsuranceTabs(
    onSelected: (TabPage) -> Unit
) {

    val headers = listOf(TabPage.BUSINESS.title, TabPage.PERSONAL.title)
    var selectedIndex by rememberSaveable { mutableStateOf(0)  }

    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        headers.forEachIndexed { index, head ->

            val selected = selectedIndex == index
            
            val backgroundColor by animateColorAsState(
                if (selected) MaterialTheme.colorScheme.secondaryContainer
                else Color.Transparent
            )

            val textColor by animateColorAsState(
                if (!selected) MaterialTheme.colorScheme.onBackground
                else MaterialTheme.colorScheme.primary
            )

            val selectedTab = if (head == TabPage.BUSINESS.title)
                TabPage.BUSINESS else TabPage.PERSONAL

            Tab(
                selected = selected,
                onClick = {
                    selectedIndex = index
                    onSelected(selectedTab)
                },
                text = {
                    Text(
                        text = head,
                        color = textColor
                    )
                },
                modifier = Modifier
                    .background(
                        backgroundColor
                    )
            )
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun InsuranceScreenPreview() {
    BritamTheme {
        InsuranceScreen(openScreen = {}) {

        }
    }
}