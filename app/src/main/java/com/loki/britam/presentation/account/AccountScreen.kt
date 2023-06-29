package com.loki.britam.presentation.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccountScreen(
    viewModel: AccountViewModel,
    openAndStart: (String) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(40.dp)
                ) {

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = viewModel.name.value, fontSize = 20.sp)
                    Text(text = viewModel.email.value, fontSize = 18.sp)
                }

            }

        }

        Button(
            onClick = { viewModel.logOut(openAndStart) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(text = "Log out")
        }
    }
}