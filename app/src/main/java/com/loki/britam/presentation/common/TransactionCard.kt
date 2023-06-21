package com.loki.britam.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.data.Transaction
import com.loki.britam.data.TransactionType

@Composable
fun TransactionCard(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {

    val color = if (TransactionType.WITHDRAWAL == transaction.type) MaterialTheme.colorScheme.error
    else Color.Green
    val sign = if (TransactionType.WITHDRAWAL == transaction.type) "-"
    else "+"

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(text = "$sign${transaction.amount}", fontSize = 18.sp, color = color)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = transaction.receiver, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        }
        Divider(
            thickness = 0.5.dp
        )
    }
}