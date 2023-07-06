package com.loki.britam.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun BoxItem(
    modifier: Modifier = Modifier,
    name: String,
    height: Dp = 70.dp,
    width: Dp = 160.dp,
    onClick: (String) -> Unit
) {

    Box(
        modifier = modifier
            .size(height = height, width = width)
            .padding(horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick(name) },
        contentAlignment = Alignment.Center
    ) {

        BackgroundCurves()

        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = Color.White.copy(.8f),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}


@Composable
fun IconBoxItem(
    modifier: Modifier = Modifier,
    name: String,
    icon: ImageVector,
    height: Dp = 70.dp,
    width: Dp = 160.dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(height = height, width = width)
            .padding(horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        IconBackgroundCurves()

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
                .size(20.dp),
            tint = Color.White.copy(.8f)
        )

        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = Color.White.copy(.8f),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.Center)
        )
    }
}