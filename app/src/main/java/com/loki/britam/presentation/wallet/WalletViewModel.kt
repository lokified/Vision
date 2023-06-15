package com.loki.britam.presentation.wallet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.loki.britam.data.contacts
import com.loki.britam.data.transactions

class WalletViewModel: ViewModel() {

    val balance = mutableStateOf("0.0")
    val contactList = contacts
    val transactionList = transactions
}