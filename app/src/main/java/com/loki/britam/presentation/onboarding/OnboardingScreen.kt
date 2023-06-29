package com.loki.britam.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.R
import com.loki.britam.presentation.navigation.Screens

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    openAndPop: (String, String) -> Unit,
    openScreen: (String) -> Unit
) {

    LaunchedEffect(key1 = viewModel.isLoggedIn.value ) {
        if (viewModel.isLoggedIn.value) {
            openAndPop(
                Screens.HomeScreen.route, Screens.OnBoardingScreen.route
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize())   {

        Image(
            painter = painterResource(id = R.drawable.onboard),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        TopSection(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopCenter),
            onClick = { openScreen(Screens.LoginScreen.route) }
        )

        BottomSection(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            onClick = { openScreen(Screens.RegisterScreen.route) }
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(modifier = modifier.fillMaxWidth()) {

        Image(
            imageVector = Icons.Filled.Grain,
            contentDescription = null,
            modifier = Modifier
                .align(
                    Alignment.Center
                )
                .size(50.dp),
            colorFilter = ColorFilter.tint(color = Color.White)
        )

        Text(
            text = "Login",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .clickable { onClick() },
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Get Started")
        }
    }
}