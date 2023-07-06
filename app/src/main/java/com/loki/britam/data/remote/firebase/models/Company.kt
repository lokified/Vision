package com.loki.britam.data.remote.firebase.models

import com.google.firebase.firestore.DocumentId

data class Company(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val businessType: String = "",
    val location: String = "",
    val description: String = "",
    val userId: String = ""
)