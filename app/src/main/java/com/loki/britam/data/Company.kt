package com.loki.britam.data

import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entriesOf

data class Company(
    val name: String,
    val data: List<Data>,
)

data class Data(
    val month: String,
    val customers: String,
    val profits: String,
    val expenditure: String,
    val revenue: String,
    val model: List<FloatEntry>,
)

val lokiBusiness = Company(
    name = "Loki",
    data = listOf(
        Data(
            month = "May",
            customers = "56",
            profits = "20,000",
            expenditure = "11,000",
            revenue = "54,000",
            model = entriesOf(0, 3, 1, 2, 3, 3)
        )
    )
)

val otherCompanies = listOf(
    Company(
        name = "Brooksite",
        data = listOf(
            Data(
                month = "May",
                customers = "22,546",
                profits = "32,256",
                expenditure = "54,125",
                revenue = "100,254",
                model = entriesOf(0,4,5,3,2,6)
            )
        )

    ),
    Company(
        name = "Krits",
        data = listOf(
            Data(
                month = "April",
                customers = "5,576",
                profits = "58,256",
                expenditure = "54,125",
                revenue = "254,124",
                model = entriesOf(0,1,1.2,1.4,2,4)
            )
        )
    ),
    Company(
        name = "Oplib",
        data = listOf(
            Data(
                month = "June",
                customers = "546",
                profits = "22,256",
                expenditure = "44,125",
                revenue = "120,254",
                model = entriesOf(4,4,2,2.4,4,5)
            )
        )
    ),
)
