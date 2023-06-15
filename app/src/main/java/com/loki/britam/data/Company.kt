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
        name = "Safaricom",
        customers = "225,576",
        profits = "3,268,256",
        expenditure = "45,154,125",
        revenue = "100,254,124"
    ),
    Company(
        name = "Airtel",
        customers = "221,546",
        profits = "322,256",
        expenditure = "544,125",
        revenue = "5,120,254"
    ),
)
