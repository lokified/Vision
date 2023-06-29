package com.loki.britam.presentation.wallet

import androidx.annotation.DrawableRes
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
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CallMissed
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.TransitEnterexit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loki.britam.R
import com.loki.britam.data.Contact
import com.loki.britam.presentation.common.HeaderSection
import com.loki.britam.presentation.common.TransactionCard
import com.loki.britam.presentation.navigation.Screens
import com.loki.britam.presentation.theme.BritamTheme

enum class ActionType {
    DEPOSIT,
    REQUEST,
    WITHDRAW,
    SEND
}

@Composable
fun WalletScreen(
    viewModel: WalletViewModel,
    openScreen: (String) -> Unit
) {

    var openSheet by rememberSaveable{ mutableStateOf(false) }
    var actionType by remember { mutableStateOf<ActionType?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {

        TopSection(
            modifier = Modifier.padding(16.dp),
            name = viewModel.name.value,
            openScreen = openScreen
        )
        
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            
            item { 
                WalletCard(
                    balance = viewModel.balance.value,
                    onClickActions = { actions ->
                        openSheet = true
                        actionType = actions
                    }
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
                    trailingContent = {
                        TextButton(onClick = { openScreen(Screens.TransactionsScreen.route)}) {
                            Text(text = "See All")
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(viewModel.transactionList) { transaction ->
                TransactionCard(
                    modifier = Modifier.padding( vertical = 8.dp),
                    transaction = transaction
                )
            }
        }
    }

    if (openSheet) {
        ActionBottomSheet(
            actionType = actionType!!,
            onClick = { /*TODO*/ },
            onDismiss = {
                openSheet = false
            }
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    name: String,
    openScreen: (String) -> Unit
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
                    text = "Hello $name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .clickable { openScreen(Screens.AccountScreen.route) }
            ) {
                
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    balance: String,
    onClickActions: (ActionType) -> Unit
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
                onClick = onClickActions
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
    onClick: (ActionType) -> Unit
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
                title = "Deposit",
                icon = Icons.Filled.ArrowUpward,
                onClick = { onClick(ActionType.DEPOSIT) }
            )

            Spacer(modifier = Modifier.weight(1f))

            ActionItem(
                title = "Request",
                icon = Icons.Filled.CallMissed,
                onClick = { onClick(ActionType.REQUEST) }
            )

            Spacer(modifier = Modifier.weight(1f))

            ActionItem(
                title = "Withdraw",
                icon = Icons.Filled.TransitEnterexit,
                onClick = { onClick(ActionType.WITHDRAW) }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            ActionItem(
                title = "Send",
                icon = Icons.Filled.Send,
                onClick = { onClick(ActionType.SEND) }
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

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp)
            .clickable { onClick() }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionBottomSheet(
    actionType: ActionType,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = setActionTypeTitle(actionType),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (actionType == ActionType.WITHDRAW || actionType == ActionType.DEPOSIT) {

                RowItem(
                    image = R.drawable.mpesa,
                    content = "Mpesa",
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = onClick
                )
                RowItem(
                    image = R.drawable.bank,
                    content = "My Bank Account",
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = onClick
                )
            }

            if (actionType == ActionType.SEND) {

                RowItem(
                    image = R.drawable.contacts,
                    content = "My Contacts",
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = onClick
                )
            }

            if (actionType == ActionType.REQUEST) {

                RowItem(
                    image = R.drawable.contacts,
                    content = "My Contacts",
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = onClick
                )
            }

            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

fun setActionTypeTitle(actionType: ActionType): String {
    return when(actionType) {
        ActionType.DEPOSIT -> "Deposit From"
        ActionType.REQUEST -> "Request From"
        ActionType.WITHDRAW -> "Withdraw To"
        ActionType.SEND -> "Send To"
    }
}

@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    @DrawableRes
    image: Int,
    content: String,
    onClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(.05f),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = content, fontSize = 15.sp)
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
                        color = MaterialTheme.colorScheme.primary.copy(.1f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { onAddContactClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.primary
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