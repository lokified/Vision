package com.loki.britam.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.data.Transaction
import com.loki.britam.data.TransactionType
import com.loki.britam.presentation.theme.BritamTheme
import com.loki.britam.presentation.wallet.WalletScreen
import com.loki.britam.presentation.wallet.WalletViewModel

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

            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = transaction.receiver[0].toString(),
                    color = color.copy(.7f),
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = transaction.receiver, fontSize = 18.sp, fontWeight = FontWeight.Normal)
                Text(text = "${transaction.date} - ${transaction.time}", fontSize = 12.sp, fontWeight = FontWeight.Normal)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$sign${transaction.amount}", fontSize = 18.sp, color = color)
        }

        Divider(
            thickness = 0.5.dp
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun WalletPreview() {
    BritamTheme {
        WalletScreen(viewModel = WalletViewModel(), openScreen = {})
    }
}