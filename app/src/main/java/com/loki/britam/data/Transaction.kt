package com.loki.britam.data

val transactions = listOf(
    Transaction(
        amount = "220,000",
        receiver = "Mpesa",
        type = TransactionType.WITHDRAWAL,
        date = "08/05/2023",
        time = "11:34 PM"
    ),

    Transaction(
        amount = "20,000",
        receiver = "My wallet",
        type = TransactionType.DEPOSIT,
        date = "08/05/2023",
        time = "11:34 PM"
    ),

    Transaction(
        amount = "220,000",
        receiver = "Drak",
        type = TransactionType.WITHDRAWAL,
        date = "08/05/2023",
        time = "11:34 PM"
    ),
    Transaction(
        amount = "100,000",
        receiver = "My Wallet",
        type = TransactionType.DEPOSIT,
        date = "08/05/2023",
        time = "11:34 PM"
    ),
    Transaction(
        amount = "100,000",
        receiver = "My Wallet",
        type = TransactionType.WITHDRAWAL,
        date = "08/05/2023",
        time = "11:34 PM"
    ),
    Transaction(
        amount = "40,000",
        receiver = "My Wallet",
        type = TransactionType.WITHDRAWAL,
        date = "08/05/2023",
        time = "11:34 PM"
    ),
    Transaction(
        amount = "100",
        receiver = "My Wallet",
        type = TransactionType.WITHDRAWAL,
        date = "08/05/2023",
        time = "11:34 PM"
    ),
    Transaction(
        amount = "1258",
        receiver = "My Wallet",
        type = TransactionType.DEPOSIT,
        date = "08/05/2023",
        time = "11:34 PM"
    )
)

data class Transaction(
    val amount: String,
    val receiver: String,
    val type: TransactionType,
    val date: String,
    val time: String
)

enum class TransactionType {
    WITHDRAWAL,
    DEPOSIT
}