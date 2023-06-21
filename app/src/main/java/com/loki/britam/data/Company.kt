package com.loki.britam.data

data class Company(
    val name: String,
    val customers: String,
    val profits: String,
    val expenditure: String,
    val revenue: String
)

val companies = listOf(
    Company(
        name = "Brooksite",
        customers = "22,546",
        profits = "32,256",
        expenditure = "54,125",
        revenue = "100,254"
    ),
    Company(
        name = "Krits",
        customers = "5,576",
        profits = "58,256",
        expenditure = "54,125",
        revenue = "254,124"
    ),
    Company(
        name = "Oplib",
        customers = "546",
        profits = "22,256",
        expenditure = "44,125",
        revenue = "120,254"
    ),
)
