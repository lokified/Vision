package com.loki.britam.presentation.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RemoveFromQueue
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.data.Contact
import com.loki.britam.data.Transaction
import com.loki.britam.data.TransactionType
import com.loki.britam.presentation.common.HeaderSection

@Composable
fun WalletScreen(
    viewModel: WalletViewModel
) {

    Column(modifier = Modifier.fillMaxSize()) {
        TopSection(modifier = Modifier.padding(16.dp))
        
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            
            item { 
                WalletCard(
                    balance = viewModel.balance.value
                )
            }
            
            item { 
                HeaderSection(
                    header = "Quick Send",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                SendSection(
                    modifier = Modifier.padding( vertical = 8.dp),
                    contacts = viewModel.contactList,
                    onAddContactClick = {}
                )
            }

            item {
                HeaderSection(
                    header = "Recent Transactions",
                    trailingText = "See All",
                    onClickTrailingText = {  },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(text = "Amount", fontSize = 12.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Recipient", fontSize = 12.sp)
                }
            }

            items(viewModel.transactionList) { transaction ->
                TransactionCard(
                    modifier = Modifier.padding( vertical = 8.dp),
                    transaction = transaction
                )
            }
        }
    }
    
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier
) {
    
    Box(modifier = modifier.fillMaxWidth()) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            
            Column(verticalArrangement = Arrangement.Center) {
                
                Text(
                    text = "My Wallet",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Hello John Doe",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            ) {
                
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    balance: String
) {
    
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(180.dp)
        ) {

            WalletInfo(
                balance = balance,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            WalletActions(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {}
            )
        } 
    }
}

@Composable
fun WalletInfo(
    modifier: Modifier = Modifier,
    balance: String
) {
    
    var isVisible by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Text(
            text = "Wallet balance",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            
            Text(
                text = "ksh $balance",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))

            val image = if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(
                    imageVector = image,
                    contentDescription = "visibility",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun WalletActions(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.Black.copy(.5f),
                shape = RoundedCornerShape(4.dp)
            ),
    ) {
        
        Row(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
        ) {
            
            ActionItem(
                title = "Request",
                icon = Icons.Filled.RequestQuote,
                onClick = onClick
            )

            Spacer(modifier = Modifier.weight(1f))

            ActionItem(
                title = "Withdraw",
                icon = Icons.Filled.RemoveFromQueue,
                onClick = onClick
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            ActionItem(
                title = "Send",
                icon = Icons.Filled.Send,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ActionItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {

    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 12.dp)
            .size(45.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = title, fontSize = 12.sp)
        }
    }
}

@Composable
fun SendSection(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    onAddContactClick: () -> Unit
) {

    LazyRow(
        modifier = modifier
    ){

        item {
            Box(
                modifier = Modifier
                    .size(height = 50.dp, width = 70.dp)
                    .padding(horizontal = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { onAddContactClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        items(contacts) { contact ->
            ContactItem(
                contact = contact,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun ContactItem(
    modifier: Modifier = Modifier,
    contact: Contact
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(4.dp)
                )
        ) {

            Image(
                painter = painterResource(id = contact.image),
                contentDescription = "contact_image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(text = contact.name, fontSize = 12.sp)
    }
}

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