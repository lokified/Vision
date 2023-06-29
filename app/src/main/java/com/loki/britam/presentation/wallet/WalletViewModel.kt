package com.loki.britam.presentation.wallet

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.loki.britam.data.contacts
import com.loki.britam.data.local.datastore.DataStoreStorage
import com.loki.britam.data.transactions
import com.loki.britam.presentation.VisionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val datastore: DataStoreStorage
): VisionViewModel(datastore) {

    val balance = mutableStateOf("0.0")
    val contactList = contacts
    val transactionList = transactions.take(3)
}