package com.loki.britam.data

import androidx.annotation.DrawableRes
import com.loki.britam.R

val contacts = listOf(
    Contact(
        name = "Doe",
        image = R.drawable.cont1
    ),
    Contact(
        name = "Remy",
        image = R.drawable.umoh
    ),
    Contact(
        name = "Drak",
        image = R.drawable.cont2
    )
)

data class Contact(
    val name: String,
    @DrawableRes
    val image: Int
)