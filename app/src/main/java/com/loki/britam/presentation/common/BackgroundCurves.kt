package com.loki.britam.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loki.britam.presentation.theme.BritamTheme

@Composable
fun BackgroundCurves() {
    BoxWithConstraints {
        val arcSize = maxHeight / 8

        Canvas(modifier = Modifier.fillMaxSize() ) {

            drawCircle(
                radius = maxHeight.toPx() / 32,
                center = Offset(arcSize.toPx() / 1.5f, arcSize.toPx() / 1.5f),
                color = Color.White.copy(.8f),
                style = Stroke(width = .5.dp.toPx())
            )

            drawArc(
                useCenter = false,
                startAngle = -88f,
                sweepAngle = 255f,
                topLeft = Offset(0f, 0f),
                color = Color.White.copy(.8f),
                size = Size(arcSize.toPx() + 8f, arcSize.toPx() + 8f),
                style = Stroke(width = .5.dp.toPx())
            )


            drawArc(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.White,
                        Color.Transparent
                    )
                ),
                startAngle = 290f,
                sweepAngle = 60f,
                useCenter = false,
                topLeft = Offset(maxWidth.toPx() / 2, arcSize.toPx() - maxHeight.toPx() / 7),
                size = Size(maxWidth.toPx() / 2, maxHeight.toPx()),
                style = Stroke(width = 1.dp.toPx())
            )

            drawArc(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.White,
                        Color.Transparent,
                    )
                ),
                startAngle = 0f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(maxWidth.toPx() / 2f , arcSize.toPx() - maxHeight.toPx() / 8),
                size = Size(maxWidth.toPx() / 2, maxHeight.toPx()),
                style = Stroke(width = 1.5.dp.toPx())
            )
        }
    }
}

@Composable
fun IconBackgroundCurves() {
    BoxWithConstraints {

        val arcSize = maxHeight / 8

        Canvas(modifier = Modifier.fillMaxSize() ) {

            drawArc(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.White,
                        Color.Transparent
                    )
                ),
                startAngle = 290f,
                sweepAngle = 60f,
                useCenter = false,
                topLeft = Offset(maxWidth.toPx() / 2, arcSize.toPx() - maxHeight.toPx() / 7),
                size = Size(maxWidth.toPx() / 2, maxHeight.toPx()),
                style = Stroke(width = 1.dp.toPx())
            )

            drawArc(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.White,
                        Color.Transparent,
                    )
                ),
                startAngle = 0f,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = Offset(maxWidth.toPx() / 2f , arcSize.toPx() - maxHeight.toPx() / 8),
                size = Size(maxWidth.toPx() / 2, maxHeight.toPx()),
                style = Stroke(width = 1.5.dp.toPx())
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun BoxItemPreview1() {
    BritamTheme {
        BoxItem(name = "Britam Loan", onClick = {})
    }
}