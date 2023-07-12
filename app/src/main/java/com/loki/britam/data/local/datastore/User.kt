package com.loki.britam.data.local.datastore

data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val isLoggedIn: Boolean = false
)
