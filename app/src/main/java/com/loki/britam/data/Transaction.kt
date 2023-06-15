package com.loki.britam.data

val transactions = listOf(
    Transaction(
        amount = "220,000",
        receiver = "Mpesa",
        type = TransactionType.WITHDRAWAL
    ),

    Transaction(
        amount = "20,000",
        receiver = "My wallet",
        type = TransactionType.DEPOSIT
    ),

    Transaction(
        amount = "220,000",
        receiver = "Drak",
        type = TransactionType.WITHDRAWAL
    ),
    Transaction(
        amount = "100,000",
        receiver = "My Wallet",
        type = TransactionType.DEPOSIT
    )
)

data class Transaction(
    val amount: String,
    val receiver: String,
    val type: TransactionType
)

enum class TransactionType {
    WITHDRAWAL,
    DEPOSIT
}